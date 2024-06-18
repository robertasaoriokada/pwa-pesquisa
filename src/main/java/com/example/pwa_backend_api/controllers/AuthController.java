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
import com.example.pwa_backend_api.dtos.RegisterDTO;
import com.example.pwa_backend_api.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  AuthService authService;
  @Autowired
  AuthenticationManager authenticationManager;
  
  @PostMapping("/login")
  public ResponseEntity<Authentication> login(@RequestBody @Validated AuthenticationDTO data){
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    Authentication auth = this.authenticationManager.authenticate(token);
    return ResponseEntity.ok(auth);
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Validated RegisterDTO data){
    authService.register(data);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/listUsers")
  public ResponseEntity<String> listUsers(){
    String lista = authService.listUsers();
    return ResponseEntity.ok(lista);
  }
}
