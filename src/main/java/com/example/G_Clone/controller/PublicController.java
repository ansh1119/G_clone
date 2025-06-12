package com.example.G_Clone.controller;


import com.example.G_Clone.entity.LoginRequest;
import com.example.G_Clone.entity.User;
import com.example.G_Clone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    UserService service;

    public PublicController(UserService service){
        this.service=service;
    }

    @PutMapping("/add-user")
    public ResponseEntity<String> createUser(@RequestBody User user){
        String token=service.newUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/login")
    public String verifyUser(@RequestBody LoginRequest loginRequest){
        System.out.println("i am here");
        return service.verify(loginRequest);
    }
}
