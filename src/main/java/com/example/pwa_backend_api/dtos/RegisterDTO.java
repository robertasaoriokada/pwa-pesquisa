package com.example.pwa_backend_api.dtos;

public record RegisterDTO(String email, String name, String password) {

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }
  
}
