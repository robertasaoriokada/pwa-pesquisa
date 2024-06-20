package com.example.pwa_backend_api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.pwa_backend_api.repositories.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepo userRepo;


  public UserDetailsServiceImpl(UserRepo userRepo) {
    this.userRepo = userRepo;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepo.findByEmail(username);
  }
  
}
