package com.example.G_Clone.repository;

import com.example.G_Clone.entity.exercise.UserExerciseStats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserExerciseStatsRepository extends MongoRepository<UserExerciseStats, ObjectId> {
    Optional<UserExerciseStats> findByUserIdAndExerciseName(String userId, String exerciseName);
}
