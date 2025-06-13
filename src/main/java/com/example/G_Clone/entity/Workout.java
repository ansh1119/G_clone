package com.example.G_Clone.entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "workout_templates")
public class Workout {

    @Id
    private ObjectId id;
    private ObjectId userId;
    private ObjectId routineId; // Optional
    private String name;
    private String description;
    private List<ObjectId> exerciseIds;
    private Date date;
    private String timeStarted; // Format: "HH:mm"
    private int durationMinutes;


    public Workout(){}

    public Workout(String name, String description, List<ObjectId> exerciseIds) {
        this.name = name;
        this.description = description;
        this.exerciseIds = exerciseIds;
    }

    public Workout(ObjectId userId, String name, String description, List<ObjectId> exerciseIds, Date date, String timeStarted, int durationMinutes) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.exerciseIds = exerciseIds;
        this.date = date;
        this.timeStarted = timeStarted;
        this.durationMinutes = durationMinutes;
    }

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getRoutineId() {
        return routineId;
    }

    public void setRoutineId(ObjectId routineId) {
        this.routineId = routineId;
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

    public List<ObjectId> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(List<ObjectId> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(String timeStarted) {
        this.timeStarted = timeStarted;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

}
