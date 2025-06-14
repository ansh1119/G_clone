package com.example.G_Clone.controller;


import com.example.G_Clone.entity.exercise.Exercise;
import com.example.G_Clone.entity.routine.RoutineTemplate;
import com.example.G_Clone.entity.user.LoginRequest;
import com.example.G_Clone.entity.user.User;
import com.example.G_Clone.entity.workout.Workout;
import com.example.G_Clone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    UserService service;

    public PublicController(UserService service){
        this.service=service;
    }

    @PutMapping("/add-user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        String token=service.newUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/login")
    public String verifyUser(@RequestBody LoginRequest loginRequest){
        System.out.println("i am here");
        return service.verify(loginRequest);
    }

    @PostMapping("/add-routine")
    public void createRoutine(@RequestBody RoutineTemplate routine){
        service.createRoutine(routine);
    }

    @PostMapping("/add-exercise")
    public void createExercise(@RequestBody Exercise exercise){
        service.createExercise(exercise);
    }

    @PostMapping("/add-workout")
    public void createWorkout(@RequestBody Workout workout){
        service.createWorkout(workout);
    }
}
