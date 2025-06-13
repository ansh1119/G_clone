package com.example.G_Clone.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user_routine")
public class UserRoutine {

    @Id
    private ObjectId id;
    private String userEmail;
    private String name;
    private String description;
    private int daysPerWeek;
    private String level; // e.g., Beginner, Intermediate
    private List<ObjectId> workoutTemplateIds;

    public UserRoutine() {
    }

    public UserRoutine(String name, String description, int daysPerWeek, String level, List<ObjectId> workoutTemplateIds) {
        this.id = new ObjectId();
        this.name = name;
        this.description = description;
        this.daysPerWeek = daysPerWeek;
        this.level = level;
        this.workoutTemplateIds = workoutTemplateIds;
    }

    // Getters and setters


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public void setDaysPerWeek(int daysPerWeek) {
        this.daysPerWeek = daysPerWeek;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<ObjectId> getWorkoutTemplateIds() {
        return workoutTemplateIds;
    }

    public void setWorkoutTemplateIds(List<ObjectId> workoutTemplateIds) {
        this.workoutTemplateIds = workoutTemplateIds;
    }
}


