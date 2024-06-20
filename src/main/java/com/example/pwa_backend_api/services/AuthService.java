package com.example.pwa_backend_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pwa_backend_api.dtos.RegisterDTO;
import com.example.pwa_backend_api.entities.User;
import com.example.pwa_backend_api.repositories.UserRepo;
import com.example.pwa_backend_api.securityconfiguration.TokenService;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenService tokenService;

    public String login(Authentication auth){
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return token;
    }

    public ResponseEntity<String> register(RegisterDTO data){
        if(userRepo.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        try {
            String encondedPassword = new BCryptPasswordEncoder().encode(data.password());

            User newUser = new User(data.getName(), data.getEmail(), encondedPassword);

            userRepo.save(newUser);
        } catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok().build();
    }

    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username);
    }

}
