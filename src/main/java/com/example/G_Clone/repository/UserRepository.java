package com.example.G_Clone.repository;

import com.example.G_Clone.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User findByName(String username);
    Optional<User> findByEmail(String email);
}
