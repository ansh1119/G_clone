package com.example.G_Clone.entity.exercise;

import java.util.List;


public class UserExercise {

    private String name;
    private List<WorkoutSet> sets;

    public UserExercise() {}

    public UserExercise(String name, List<WorkoutSet> sets) {
        this.name = name;
        this.sets = sets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkoutSet> getSets() {
        return sets;
    }

    public void setSets(List<WorkoutSet> sets) {
        this.sets = sets;
    }


    // Getters and setters
}
