package com.example.G_Clone.service;


import com.example.G_Clone.entity.LoginRequest;
import com.example.G_Clone.entity.User;
import com.example.G_Clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    JWTService service;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    public String newUser(User user) {
        Optional<User> existingUser = repository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            // Return 409 Conflict with a descriptive message
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return service.generateToken(user.getEmail()); // Use email for token
    }

    public String verify(LoginRequest request) {
        Optional<User> optionalUser = repository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Return JWT token
        return service.generateToken(user.getEmail());
    }
}
