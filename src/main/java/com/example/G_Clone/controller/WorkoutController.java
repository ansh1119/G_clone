package com.example.G_Clone.controller;


import com.example.G_Clone.entity.workout.UserWorkout;
import com.example.G_Clone.entity.workout.Workout;
import com.example.G_Clone.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/next")
    public ResponseEntity<UserWorkout> getNextWorkout(Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            UserWorkout nextWorkout = workoutService.getNextWorkout(userEmail);
            return ResponseEntity.ok(nextWorkout);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/complete")
    public ResponseEntity<String> completeWorkout(@RequestBody UserWorkout workout, Authentication authentication) {
        String email=authentication.getName();
        workoutService.completeWorkout(email, workout);
        return ResponseEntity.ok("Workout completed and progress updated.");
    }
}
