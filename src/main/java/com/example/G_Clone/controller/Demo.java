package com.example.G_Clone.controller;


import com.example.G_Clone.entity.User;
import com.example.G_Clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Demo {

    UserService service;

    public Demo(UserService service){
        this.service=service;
    }

    @PostMapping("/add-user")
    public void getHello(@RequestBody User user){
        service.addUser(user);
    }
}
