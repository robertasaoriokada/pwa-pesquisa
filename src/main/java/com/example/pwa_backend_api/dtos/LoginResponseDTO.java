package com.example.pwa_backend_api.dtos;

public record LoginResponseDTO(String token) {

  public String token() {
    return token;
  }
  
}
