package com.example.G_Clone.util;


import java.util.HashMap;
import java.util.Map;

public class MuscleGroupMapper {

    private static final Map<String, String> exerciseToMuscle = new HashMap<>();

    static {
        // Chest
        exerciseToMuscle.put("Barbell Bench Press", "Chest");
        exerciseToMuscle.put("Incline Bench Press", "Chest");
        exerciseToMuscle.put("Dumbbell Incline Bench Press", "Chest");
        exerciseToMuscle.put("Chest Press Machine", "Chest");

        // Back
        exerciseToMuscle.put("Pull Up", "Back");
        exerciseToMuscle.put("Lat Pulldown", "Back");
        exerciseToMuscle.put("Barbell Row", "Back");
        exerciseToMuscle.put("Seated Cable Row", "Back");

        // Legs
        exerciseToMuscle.put("Barbell Squat", "Legs");
        exerciseToMuscle.put("Leg Press", "Legs");
        exerciseToMuscle.put("Lunges", "Legs");
        exerciseToMuscle.put("Hack Squat", "Legs");

        // Hamstrings / Posterior Chain
        exerciseToMuscle.put("Deadlift", "Hamstrings");
        exerciseToMuscle.put("Romanian Deadlift", "Hamstrings");
        exerciseToMuscle.put("Lying Leg Curl", "Hamstrings");

        // Shoulders
        exerciseToMuscle.put("Overhead Press", "Shoulders");
        exerciseToMuscle.put("Dumbbell Shoulder Press", "Shoulders");
        exerciseToMuscle.put("Lateral Raise", "Shoulders");

        // Biceps
        exerciseToMuscle.put("Barbell Curl", "Biceps");
        exerciseToMuscle.put("Dumbbell Curl", "Biceps");
        exerciseToMuscle.put("Preacher Curl", "Biceps");

        // Triceps
        exerciseToMuscle.put("Tricep Pushdown", "Triceps");
        exerciseToMuscle.put("Overhead Tricep Extension", "Triceps");
        exerciseToMuscle.put("Close Grip Bench Press", "Triceps");

        // Add more mappings based on your app's exercise list
    }

    public static String getGroup(String exerciseName) {
        return exerciseToMuscle.getOrDefault(exerciseName, "Other");
    }


}

