package com.example.pwa_backend_api.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ANSWERS")
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(name = "content")
  private String content;

  @Column(name = "point")
  private String point;

  @ManyToOne
  @JoinColumn(name = "answer_id", nullable = false)
  private Questions question;

  @Column(name = "created_at")
  private Timestamp created_at;
  
  @Column(name = "updated_at")
  private Timestamp updated_at;
}
