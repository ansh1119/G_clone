package com.example.G_Clone.service;

import com.example.G_Clone.entity.User;
import com.example.G_Clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByName(username);
        if(user==null)
            throw new UsernameNotFoundException("username not found "+username);
        else
            return org.springframework.security.core.userdetails.User.withUsername(user.getName())
                    .password(user.getPassword())
                    .build();
    }
}