package com.example.G_Clone.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Document
public class User {

    @Id
    private ObjectId id;
    private String email;
    private String password;
    private String profileImage;
    private int age;
    private Height height;
    private String gender;
    private int weight;
    private int weeklyGoal;
    private ObjectId currentRoutineId;
    private List<ObjectId> workoutIds;
    private List<ObjectId> friendIds;
    private List<ObjectId> favoriteWorkoutIds;
    private float bmi;
    private float currentStrengthScore;
    private List<StrengthScoreEntry> strengthScoreHistory;
    private Map<String, Float> muscleStrengthScores;
    private Map<String, Date> muscleRecoveryStatus;

    public User(ObjectId id, String email, String password, String profileImage, int age, Height height, String gender, int weight, int weeklyGoal, ObjectId currentRoutineId, List<ObjectId> workoutIds, List<ObjectId> friendIds, List<ObjectId> favoriteWorkoutIds, float bmi, float currentStrengthScore, List<StrengthScoreEntry> strengthScoreHistory, Map<String, Float> muscleStrengthScores, Map<String, Date> muscleRecoveryStatus) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.weight = weight;
        this.weeklyGoal = weeklyGoal;
        this.currentRoutineId = currentRoutineId;
        this.workoutIds = workoutIds;
        this.friendIds = friendIds;
        this.favoriteWorkoutIds = favoriteWorkoutIds;
        this.bmi = bmi;
        this.currentStrengthScore = currentStrengthScore;
        this.strengthScoreHistory = strengthScoreHistory;
        this.muscleStrengthScores = muscleStrengthScores;
        this.muscleRecoveryStatus = muscleRecoveryStatus;
    }

    public User( String email, String password, String profileImage, int age, Height height, String gender, int weight, int weeklyGoal, ObjectId currentRoutineId, List<ObjectId> workoutIds, List<ObjectId> friendIds, List<ObjectId> favoriteWorkoutIds, float bmi, float currentStrengthScore, List<StrengthScoreEntry> strengthScoreHistory, Map<String, Float> muscleStrengthScores, Map<String, Date> muscleRecoveryStatus) {
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.weight = weight;
        this.weeklyGoal = weeklyGoal;
        this.currentRoutineId = currentRoutineId;
        this.workoutIds = workoutIds;
        this.friendIds = friendIds;
        this.favoriteWorkoutIds = favoriteWorkoutIds;
        this.bmi = bmi;
        this.currentStrengthScore = currentStrengthScore;
        this.strengthScoreHistory = strengthScoreHistory;
        this.muscleStrengthScores = muscleStrengthScores;
        this.muscleRecoveryStatus = muscleRecoveryStatus;
    }


    public User(){}

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(int weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }

    public ObjectId getCurrentRoutineId() {
        return currentRoutineId;
    }

    public void setCurrentRoutineId(ObjectId currentRoutineId) {
        this.currentRoutineId = currentRoutineId;
    }

    public List<ObjectId> getWorkoutIds() {
        return workoutIds;
    }

    public void setWorkoutIds(List<ObjectId> workoutIds) {
        this.workoutIds = workoutIds;
    }

    public List<ObjectId> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(List<ObjectId> friendIds) {
        this.friendIds = friendIds;
    }

    public List<ObjectId> getFavoriteWorkoutIds() {
        return favoriteWorkoutIds;
    }

    public void setFavoriteWorkoutIds(List<ObjectId> favoriteWorkoutIds) {
        this.favoriteWorkoutIds = favoriteWorkoutIds;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public float getCurrentStrengthScore() {
        return currentStrengthScore;
    }

    public void setCurrentStrengthScore(float currentStrengthScore) {
        this.currentStrengthScore = currentStrengthScore;
    }

    public List<StrengthScoreEntry> getStrengthScoreHistory() {
        return strengthScoreHistory;
    }

    public void setStrengthScoreHistory(List<StrengthScoreEntry> strengthScoreHistory) {
        this.strengthScoreHistory = strengthScoreHistory;
    }

    public Map<String, Float> getMuscleStrengthScores() {
        return muscleStrengthScores;
    }

    public void setMuscleStrengthScores(Map<String, Float> muscleStrengthScores) {
        this.muscleStrengthScores = muscleStrengthScores;
    }

    public Map<String, Date> getMuscleRecoveryStatus() {
        return muscleRecoveryStatus;
    }

    public void setMuscleRecoveryStatus(Map<String, Date> muscleRecoveryStatus) {
        this.muscleRecoveryStatus = muscleRecoveryStatus;
    }
}

