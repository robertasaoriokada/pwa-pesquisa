package com.example.pwa_backend_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pwa_backend_api.connection.EntityManagerProvider;
import com.example.pwa_backend_api.dtos.AuthenticationDTO;
import com.example.pwa_backend_api.dtos.RegisterDTO;
import com.example.pwa_backend_api.entities.User;
import com.example.pwa_backend_api.repositories.UserRepo;
import com.example.pwa_backend_api.securityconfiguration.TokenService;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;
    @Autowired
    EntityManagerProvider entityManagerProvider;

    public String login(AuthenticationDTO data){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());
        return token;
    }

    public ResponseEntity<String> register(RegisterDTO data){
        if(userRepo.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        
        String encondedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.getName(), data.getEmail(), encondedPassword);

        this.userRepo.save(newUser);
        return ResponseEntity.ok().build();
    }

    public List<User> listUsers() {
        return userRepo.findAll();
    }

}
