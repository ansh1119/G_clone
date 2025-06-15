package com.example.G_Clone.entity.exercise;


public class WorkoutSet {

    private int targetReps;
    private float targetWeight;
    private Integer performedReps;
    private Float performedWeight;
    private boolean isCompleted;

    public WorkoutSet() {}

    public WorkoutSet(int targetReps, float targetWeight) {
        this.targetReps = targetReps;
        this.targetWeight = targetWeight;
        this.isCompleted = false;
    }

    public int getTargetReps() {
        return targetReps;
    }

    public void setTargetReps(int targetReps) {
        this.targetReps = targetReps;
    }

    public float getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(float targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Integer getPerformedReps() {
        return performedReps;
    }

    public void setPerformedReps(Integer performedReps) {
        this.performedReps = performedReps;
    }

    public Float getPerformedWeight() {
        return performedWeight;
    }

    public void setPerformedWeight(Float performedWeight) {
        this.performedWeight = performedWeight;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }


    // Getters and setters
}
