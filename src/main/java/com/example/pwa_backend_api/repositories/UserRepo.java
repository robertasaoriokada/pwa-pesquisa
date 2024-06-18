package com.example.pwa_backend_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.pwa_backend_api.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{
  default UserDetails findByEmail(String email){
    return null;
  }
} 
