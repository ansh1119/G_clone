package com.example.G_Clone.repository;

import com.example.G_Clone.entity.routine.RoutineTemplate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoutineRepository extends MongoRepository<RoutineTemplate, ObjectId> {
    Optional<RoutineTemplate> findById(String routineID);
}
