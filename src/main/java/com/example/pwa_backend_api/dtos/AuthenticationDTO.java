package com.example.pwa_backend_api.dtos;

public record AuthenticationDTO(String email, String password) {

  public String email() {
    return email;
  }

  public String password() {
    return password;
  }

} 
