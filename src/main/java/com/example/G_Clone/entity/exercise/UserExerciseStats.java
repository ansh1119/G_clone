package com.example.G_Clone.entity.exercise;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "user_exercise_stats")
@CompoundIndex(def = "{'userId': 1, 'exerciseName': 1}", unique = true)
public class UserExerciseStats {

    @Id
    private ObjectId id;

    private String userId;
    private String exerciseName; // Or exerciseTemplateId if you prefer stronger linkage

    private Float maxWeight;
    private Integer maxReps;
    private Float maxVolume;

    private LocalDate lastUpdated;

    private List<HistoricalSet> history; // All previous performed sets


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

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getMaxReps() {
        return maxReps;
    }

    public void setMaxReps(Integer maxReps) {
        this.maxReps = maxReps;
    }

    public Float getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Float maxVolume) {
        this.maxVolume = maxVolume;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<HistoricalSet> getHistory() {
        return history;
    }

    public void setHistory(List<HistoricalSet> history) {
        this.history = history;
    }
}
