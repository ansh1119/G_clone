package com.example.G_Clone.controller;


import com.example.G_Clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/choose-routine/{routineID}/{email}")
    public void chooseRoutine(@PathVariable String routineID,@PathVariable String email){
        userService.chooseRoutine(email,routineID);
    }
}
