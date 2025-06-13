package com.example.G_Clone.repository;

import com.example.G_Clone.entity.RoutineTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoutineRepository extends MongoRepository<RoutineTemplate, ObjectId> {
    Optional<Object> findById(String routineID);
}
