package com.example.G_Clone.entity.user;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "users")
public class User {

    @Id
    private String email;

    private String name;
    private String password;
    private String profileImage;
    private int age;
    private Height height;
    private String gender;
    private int weight;
    private float bmi;
    private int weeklyGoal;

    private ObjectId currentRoutineId;
    private List<ObjectId> workoutIds;
    private List<ObjectId> friendIds;
    private List<ObjectId> favoriteWorkoutIds;

    private int currentWorkoutNumber;

    // Current strength score (avg across muscle groups)
    private float currentStrengthScore;

    // Historical strength scores per muscle group
    private Map<String, List<StrengthScoreEntry>> strengthScores = new HashMap<>();

    // Latest/average score per muscle group (for quick dashboard access)
    private Map<String, Float> muscleStrengthScores = new HashMap<>();

    // Recovery tracking
    private Map<String, Date> muscleRecoveryStatus = new HashMap<>();
    public User(String name,String email, String password, String profileImage, int age, Height height, String gender, int weight, int weeklyGoal, ObjectId currentRoutineId, List<ObjectId> workoutIds, List<ObjectId> friendIds, List<ObjectId> favoriteWorkoutIds, float bmi, float currentStrengthScore, List<StrengthScoreEntry> strengthScoreHistory, Map<String, Float> muscleStrengthScores, Map<String, Date> muscleRecoveryStatus,int currentWorkoutNumber) {
        this.name=name;
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
        this.muscleStrengthScores = muscleStrengthScores;
        this.muscleRecoveryStatus = muscleRecoveryStatus;
        this.currentWorkoutNumber=currentWorkoutNumber;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public Map<String, List<StrengthScoreEntry>> getStrengthScores() {
        return strengthScores;
    }

    public void setStrengthScores(Map<String, List<StrengthScoreEntry>> strengthScores) {
        this.strengthScores = strengthScores;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentWorkoutNumber() {
        return currentWorkoutNumber;
    }

    public void setCurrentWorkoutNumber(int currentWorkoutNumber) {
        this.currentWorkoutNumber = currentWorkoutNumber;
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

