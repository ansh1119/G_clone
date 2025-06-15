package com.example.G_Clone.entity.workout;


import com.example.G_Clone.entity.exercise.UserExercise;
import com.example.G_Clone.entity.exercise.UserExerciseStats;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "user_workouts")
public class UserWorkout {

    @Id
    private ObjectId id;

    private String userId; // typically the user's email
    private ObjectId routineId; // optional: to trace the original routine/template
    private int dayNumber; // e.g., 0 = Push, 1 = Pull, etc.
    private String workoutName;
    private List<UserExercise> exercises;
    @Transient // Do NOT store this in MongoDB
    private Map<String, UserExerciseStats> exerciseStatsMap;

    private boolean completed;
    private Date completionDate;

    private String notes;

    public UserWorkout() {}

    public UserWorkout(ObjectId id, String userId, ObjectId routineId, int dayNumber, String workoutName, List<UserExercise> exercises, boolean completed, Date completionDate, String notes) {
        this.id = id;
        this.userId = userId;
        this.routineId = routineId;
        this.dayNumber = dayNumber;
        this.workoutName = workoutName;
        this.exercises = exercises;
        this.completed = completed;
        this.completionDate = completionDate;
        this.notes = notes;
    }

    public Map<String, UserExerciseStats> getExerciseStatsMap() {
        return exerciseStatsMap;
    }

    public void setExerciseStatsMap(Map<String, UserExerciseStats> exerciseStatsMap) {
        this.exerciseStatsMap = exerciseStatsMap;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ObjectId getRoutineId() {
        return routineId;
    }

    public void setRoutineId(ObjectId routineId) {
        this.routineId = routineId;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public List<UserExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<UserExercise> exercises) {
        this.exercises = exercises;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
