package com.example.G_Clone.entity.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class StrengthScoreEntry {

    private Date timestamp;

    private double totalScore;

    private Map<String, Double> muscleGroupScores;

    // Getters and Setters
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public Map<String, Double> getMuscleGroupScores() {
        return muscleGroupScores;
    }

    public void setMuscleGroupScores(Map<String, Double> muscleGroupScores) {
        this.muscleGroupScores = muscleGroupScores;
    }
}
