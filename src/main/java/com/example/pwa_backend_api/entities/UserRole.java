package com.example.pwa_backend_api.entities;

public enum UserRole {
  USER("user"), ADMIN("admin"), SEARCHER("searcher");

  private String role;

  UserRole(String role){
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
  
}
