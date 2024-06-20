package com.example.pwa_backend_api.connection;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EntityScan(basePackages = "com.example.pwa_backend_api.entities")
@EnableJpaRepositories(basePackages = "com.example.pwa_backend_api.repositories")
public class EntityManagerProvider {
   @Value("${spring.datasource.url}")
   private String datasourceUrl;
  
}

