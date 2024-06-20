package com.example.pwa_backend_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forms")
public class FormsController {
  
  @GetMapping
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok().build();
  }
}
