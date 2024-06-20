package com.example.pwa_backend_api.securityconfiguration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.pwa_backend_api.entities.User;

@Service
public class TokenService {
  @Value("{api.security.token.secret}")
  private String secret;

  public String generateToken(User user){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
      .withIssuer("pwa-backend-api")
      .withSubject(user.getEmail())
      .withExpiresAt(generateExpirationDate())
      .sign(algorithm);
      return token;
    }catch(JWTCreationException exception){
      throw new RuntimeException("Error while generating token!");
    }
  }

  public String validateToken(String token){
    try{
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
      .withIssuer("pwa-backend-api")
      .build()
      .verify(token)
      .getSubject();
    } catch(JWTVerificationException exception){
      return "";
    }
  }

  private Instant generateExpirationDate(){
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-3:00"));
  }
  
}
