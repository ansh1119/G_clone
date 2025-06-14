package com.example.G_Clone.service;

import com.example.G_Clone.entity.user.User;
import com.example.G_Clone.entity.UserPrincipal;
import com.example.G_Clone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findById(email).orElse(null);

        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        else{
            System.out.println("loading user..."+ user.getEmail());
        }

        return new UserPrincipal(user);
    }
}