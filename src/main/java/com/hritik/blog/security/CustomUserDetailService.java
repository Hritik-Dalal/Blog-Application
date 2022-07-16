package com.hritik.blog.security;

import com.hritik.blog.entities.User;
import com.hritik.blog.exception.ResourceNotFoundException;
import com.hritik.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with email : %s", username)));
        return user;
    }
}
