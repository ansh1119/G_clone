package com.example.G_Clone.repository;

import com.example.G_Clone.entity.routine.UserRoutine;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRoutineRepository extends MongoRepository<UserRoutine, ObjectId> {

    Optional<UserRoutine> findByUserEmail(String userEmail);
}
