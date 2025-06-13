package com.example.G_Clone.repository;

import com.example.G_Clone.entity.Workout;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutRepository extends MongoRepository<Workout, ObjectId> {
}
