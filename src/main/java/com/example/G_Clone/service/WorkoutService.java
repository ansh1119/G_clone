package com.example.G_Clone.service;


import com.example.G_Clone.entity.exercise.Exercise;
import com.example.G_Clone.entity.exercise.UserExercise;
import com.example.G_Clone.entity.routine.RoutineTemplate;
import com.example.G_Clone.entity.routine.UserRoutine;
import com.example.G_Clone.entity.user.User;
import com.example.G_Clone.entity.workout.UserWorkout;
import com.example.G_Clone.entity.workout.Workout;
import com.example.G_Clone.entity.workout.WorkoutSet;
import com.example.G_Clone.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public UserWorkout getNextWorkout(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getCurrentRoutineId().toString());
        ObjectId routineId = user.getCurrentRoutineId();
        UserRoutine routine = userRoutineRepository.findById(routineId)
                .orElseThrow(() -> new RuntimeException("Routine not found"));

        int dayNumber = user.getCurrentWorkoutNumber() % routine.getDaysPerWeek();
        ObjectId workoutTemplateId = routine.getWorkoutTemplateIds().get(dayNumber);

        Workout workoutTemplate = workoutTemplateRepository.findById(workoutTemplateId)
                .orElseThrow(() -> new RuntimeException("Workout template not found"));

        // Convert workout template's exerciseIds to UserExercises
        List<UserExercise> userExercises = workoutTemplate.getExerciseIds()
                .stream()
                .map(exerciseId -> {
                    Exercise ex = exerciseRepository.findById(exerciseId)
                            .orElseThrow(() -> new RuntimeException("Exercise not found"));
                    return convertToUserExercise(ex);
                })
                .collect(Collectors.toList());

        // Create UserWorkout
        UserWorkout userWorkout = new UserWorkout();
        userWorkout.setUserId(userEmail);
        userWorkout.setRoutineId(routineId);
        userWorkout.setDayNumber(dayNumber);
        userWorkout.setWorkoutName(workoutTemplate.getName());
        userWorkout.setExercises(userExercises);
        userWorkout.setCompleted(false);
        userWorkout.setCompletionDate(null);
        userWorkout.setNotes("");


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
        UserRoutine userRoutine =  userRoutineRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User routine not found"));

        int currentWorkout = user.getCurrentWorkoutNumber();
        int totalDays = userRoutine.getDaysPerWeek();
        int nextWorkout = (currentWorkout + 1) % totalDays;

        // 5. Update user’s current workout number
        user.setCurrentWorkoutNumber(nextWorkout);
        userRepository.save(user);
    }



}
