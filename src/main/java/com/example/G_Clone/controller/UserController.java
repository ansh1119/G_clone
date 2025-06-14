package com.example.G_Clone.controller;


import com.example.G_Clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/choose-routine/{routineID}")
    public void chooseRoutine(@PathVariable String routineID, Authentication authentication) {
        System.out.println("reached");
        String email = authentication.getName(); // gets email from JWT
        System.out.println("from controller "+email);
        userService.chooseRoutine(email, routineID);
    }


}
