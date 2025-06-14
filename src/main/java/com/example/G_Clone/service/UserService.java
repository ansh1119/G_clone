package com.example.G_Clone.service;


import com.example.G_Clone.entity.exercise.Exercise;
import com.example.G_Clone.entity.routine.RoutineTemplate;
import com.example.G_Clone.entity.routine.UserRoutine;
import com.example.G_Clone.entity.user.LoginRequest;
import com.example.G_Clone.entity.user.User;
import com.example.G_Clone.entity.workout.Workout;
import com.example.G_Clone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    JWTService service;

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRoutineRepository userRoutineRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public String newUser(User user) {
        Optional<User> existingUser = repository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            // Return 409 Conflict with a descriptive message
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return service.generateToken(user.getEmail()); // Use email for token
    }

    public String verify(LoginRequest request) {
        Optional<User> optionalUser = repository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Return JWT token
        return service.generateToken(user.getEmail());
    }

    public void createRoutine(RoutineTemplate routine) {
        routineRepository.save(routine);
    }

    public void createExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
    }

    public void createWorkout(Workout workout) {
        workoutRepository.save(workout);
    }

    public void chooseRoutine(String email, String routineTemplateId) {
        // Fetch user
        User user = repository.findById(email).orElseThrow(() -> new RuntimeException("Invalid user"));

        // Fetch routine template
        RoutineTemplate template = routineRepository.findById(routineTemplateId).orElseThrow(() -> new RuntimeException("Invalid routine template"));

        // Create a new UserRoutine from the template
        UserRoutine userRoutine = new UserRoutine();
        userRoutine.setName(template.getName());
        userRoutine.setDescription(template.getDescription());
        userRoutine.setDaysPerWeek(template.getDaysPerWeek());
        userRoutine.setLevel(template.getLevel());
        userRoutine.setWorkoutTemplateIds(template.getWorkoutTemplateIds());
        userRoutine.setUserEmail(user.getEmail()); // Link the user

        // Save the user routine
        UserRoutine savedUserRoutine = userRoutineRepository.save(userRoutine);

        // Set user's current routine
        user.setCurrentRoutineId(savedUserRoutine.getId());
        repository.save(user);
    }

}
