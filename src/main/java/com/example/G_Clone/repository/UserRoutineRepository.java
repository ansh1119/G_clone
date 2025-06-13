package com.example.G_Clone.repository;

import com.example.G_Clone.entity.UserRoutine;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRoutineRepository extends MongoRepository<UserRoutine, ObjectId> {
}
