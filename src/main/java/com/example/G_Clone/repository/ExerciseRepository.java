package com.example.G_Clone.repository;

import com.example.G_Clone.entity.Exercise;
import com.example.G_Clone.entity.RoutineTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<Exercise, ObjectId> {
}