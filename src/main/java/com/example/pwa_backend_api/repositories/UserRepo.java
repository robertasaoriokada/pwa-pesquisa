package com.example.pwa_backend_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pwa_backend_api.entities.User;
import com.example.pwa_backend_api.entities.UserDetailsAuthenticated;


@Repository
public interface UserRepo extends JpaRepository<User, String>{
  UserDetailsAuthenticated findByEmail(String email);
  
} 
