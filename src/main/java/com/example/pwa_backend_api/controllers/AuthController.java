package com.example.pwa_backend_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pwa_backend_api.dtos.AuthenticationDTO;
import com.example.pwa_backend_api.dtos.LoginResponseDTO;
import com.example.pwa_backend_api.dtos.RegisterDTO;
import com.example.pwa_backend_api.entities.User;
import com.example.pwa_backend_api.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  AuthService authService;
  @Autowired 
  AuthenticationManager authenticationManager;
  
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated AuthenticationDTO data){
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);
    return ResponseEntity.ok(new LoginResponseDTO(authService.login(auth)));
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Validated RegisterDTO data){
    return authService.register(data);
  }

  @GetMapping("/listUsers")
  public ResponseEntity<java.util.List<User>> listUsers(){
    return ResponseEntity.ok(authService.listUsers());
  }

  @GetMapping("/hello")
  public ResponseEntity<String> parabens(){
    return ResponseEntity.ok("EBAAAAAAAAAA");
  }
}
