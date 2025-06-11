package com.example.G_Clone.service;


import com.example.G_Clone.entity.User;
import com.example.G_Clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;


    public void addUser(User user){
        repository.save(user);
    }
}
