package com.example.G_Clone.repository;

import com.example.G_Clone.entity.workout.UserWorkout;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserWorkoutRepository extends MongoRepository<UserWorkout, ObjectId> {
}
