package com.example.G_Clone.service;


import com.example.G_Clone.OneRMCalculator;
import com.example.G_Clone.entity.exercise.*;
import com.example.G_Clone.entity.routine.UserRoutine;
import com.example.G_Clone.entity.user.StrengthScoreEntry;
import com.example.G_Clone.entity.user.User;
import com.example.G_Clone.entity.workout.UserWorkout;
import com.example.G_Clone.entity.workout.Workout;
import com.example.G_Clone.repository.*;
import com.example.G_Clone.util.MuscleGroupMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoutineRepository routineTemplateRepository;

    @Autowired
    private WorkoutRepository workoutTemplateRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserWorkoutRepository userWorkoutRepository;

    @Autowired
    private UserRoutineRepository userRoutineRepository;

    @Autowired
    private UserExerciseStatsRepository statsRepository;

    public UserWorkout getNextWorkout(String userEmail) {
        // 1. Fetch user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ObjectId routineId = user.getCurrentRoutineId();

        // 2. Fetch user routine
        UserRoutine routine = userRoutineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Routine not found"));

        // 3. Determine workout for the current day
        int dayNumber = user.getCurrentWorkoutNumber() % routine.getDaysPerWeek();

        System.out.println("Welcome to day "+dayNumber);
        ObjectId workoutTemplateId = routine.getWorkoutTemplateIds().get(dayNumber);

        // 4. Fetch workout template
        Workout workoutTemplate = workoutTemplateRepository.findById(workoutTemplateId)
                .orElseThrow(() -> new RuntimeException("Workout template not found"));

        // 5. Convert each Exercise template to UserExercise
        List<UserExercise> userExercises = new ArrayList<>();
        Map<String, UserExerciseStats> exerciseStatsMap = new HashMap<>();

        for (ObjectId exerciseId : workoutTemplate.getExerciseIds()) {
            Exercise ex = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new RuntimeException("Exercise not found"));

            // Convert to UserExercise
            UserExercise userExercise = convertToUserExercise(ex);
            userExercises.add(userExercise);

            // Fetch UserExerciseStats for this exercise if it exists
            statsRepository.findByUserIdAndExerciseName(userEmail, ex.getName())
                    .ifPresent(stats -> exerciseStatsMap.put(ex.getName(), stats));
        }

        // 6. Build UserWorkout
        UserWorkout userWorkout = new UserWorkout();
        userWorkout.setUserId(userEmail);
        userWorkout.setRoutineId(routineId);
        userWorkout.setDayNumber(dayNumber);
        userWorkout.setWorkoutName(workoutTemplate.getName());
        userWorkout.setExercises(userExercises);
        userWorkout.setCompleted(false);
        userWorkout.setCompletionDate(null);
        userWorkout.setNotes("");

        // 7. Set the stats map (won’t be saved due to @Transient)
        userWorkout.setExerciseStatsMap(exerciseStatsMap);

        return userWorkout;
    }

    private UserExercise convertToUserExercise(Exercise exercise) {
        List<WorkoutSet> sets = new ArrayList<>();

        // Add one default empty set
        WorkoutSet defaultSet = new WorkoutSet();
        defaultSet.setTargetReps(0);
        defaultSet.setTargetWeight(0f);
        defaultSet.setPerformedReps(null);
        defaultSet.setPerformedWeight(null);
        defaultSet.setIsCompleted(false);

        sets.add(defaultSet);

        return new UserExercise(exercise.getName(), sets);
    }


    public void completeWorkout(String userEmail, UserWorkout completedWorkout) {
        // 1. Mark workout as completed
        completedWorkout.setCompleted(true);
        completedWorkout.setCompletionDate(new Date());

        // 2. Save the workout to DB
        userWorkoutRepository.save(completedWorkout);

        // 3. Fetch user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 4. Fetch user's routine to get daysPerWeek
        UserRoutine userRoutine = userRoutineRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User routine not found"));

        int currentWorkout = user.getCurrentWorkoutNumber();
        int totalDays = userRoutine.getDaysPerWeek();
        int nextWorkout = (currentWorkout + 1) % totalDays;

        // 5. Update user’s current workout number
        user.setCurrentWorkoutNumber(nextWorkout);

        float bodyWeight = user.getWeight(); // Bodyweight in kg — required for strength score
        if (bodyWeight <= 0) {
            throw new IllegalStateException("User body weight is required for strength score calculation.");
        }

        // 6. Update or create exercise stats and strength scores
        for (UserExercise userExercise : completedWorkout.getExercises()) {
            String exerciseName = userExercise.getName();

            float maxWeight = 0f;
            int maxReps = 0;
            float maxVolume = 0f;
            float bestOneRM = 0f;

            List<HistoricalSet> newHistory = new ArrayList<>();

            for (WorkoutSet set : userExercise.getSets()) {
                if (!set.getIsCompleted()) continue;

                int reps = set.getPerformedReps() != null ? set.getPerformedReps() : 0;
                float weight = set.getPerformedWeight() != null ? set.getPerformedWeight() : 0f;
                float volume = reps * weight;

                maxWeight = Math.max(maxWeight, weight);
                maxReps = Math.max(maxReps, reps);
                maxVolume = Math.max(maxVolume, volume);

                float oneRM = OneRMCalculator.calculateOneRM(weight, reps);
                bestOneRM = Math.max(bestOneRM, oneRM);

                HistoricalSet historicalSet = new HistoricalSet();
                historicalSet.setDate(LocalDate.now());
                historicalSet.setReps(reps);
                historicalSet.setWeight(weight);
                historicalSet.setWorkoutId(completedWorkout.getId().toString());

                newHistory.add(historicalSet);
            }

            if (newHistory.isEmpty()) continue;

            // Update stats
            Optional<UserExerciseStats> optionalStats = statsRepository
                    .findByUserIdAndExerciseName(userEmail, exerciseName);

            if (optionalStats.isPresent()) {
                UserExerciseStats stats = optionalStats.get();
                stats.setMaxWeight(Math.max(stats.getMaxWeight(), maxWeight));
                stats.setMaxReps(Math.max(stats.getMaxReps(), maxReps));
                stats.setMaxVolume(Math.max(stats.getMaxVolume(), maxVolume));
                stats.setLastUpdated(LocalDate.now());
                stats.getHistory().addAll(newHistory);
                statsRepository.save(stats);
            } else {
                UserExerciseStats newStats = new UserExerciseStats();
                newStats.setUserId(userEmail);
                newStats.setExerciseName(exerciseName);
                newStats.setMaxWeight(maxWeight);
                newStats.setMaxReps(maxReps);
                newStats.setMaxVolume(maxVolume);
                newStats.setLastUpdated(LocalDate.now());
                newStats.setHistory(newHistory);
                statsRepository.save(newStats);
            }

            // --- Strength Score Update (Gravl style) ---
            String muscleGroup = MuscleGroupMapper.getGroup(exerciseName);

            if (bestOneRM > 0 && bodyWeight > 0) {
                float normalizedScore = (bestOneRM / bodyWeight) * 100;

                StrengthScoreEntry scoreEntry = new StrengthScoreEntry();
                scoreEntry.setTimestamp(new Date());
                scoreEntry.setTotalScore(normalizedScore);

                user.getStrengthScores()
                        .computeIfAbsent(muscleGroup, k -> new ArrayList<>())
                        .add(scoreEntry);

                // Update muscle group average
                List<StrengthScoreEntry> allEntries = user.getStrengthScores().get(muscleGroup);
                double avg = allEntries.stream()
                        .mapToDouble(StrengthScoreEntry::getTotalScore)
                        .average()
                        .orElse(0);

                user.getMuscleStrengthScores().put(muscleGroup, (float) avg);
            }
        }

        // 7. Update currentStrengthScore (average of all muscle group scores)
        double overallAvg = user.getMuscleStrengthScores()
                .values()
                .stream()
                .mapToDouble(Float::doubleValue)
                .average()
                .orElse(0);

        user.setCurrentStrengthScore((float) overallAvg);

        // 8. Save user
        userRepository.save(user);
    }



}
