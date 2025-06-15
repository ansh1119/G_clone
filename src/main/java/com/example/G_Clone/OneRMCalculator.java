package com.example.G_Clone;

public class OneRMCalculator {
    public static float calculateOneRM(float weight, int reps) {
        if (reps <= 1) return weight;
        return weight * (1 + reps / 30.0f); // Epley formula
    }
}
