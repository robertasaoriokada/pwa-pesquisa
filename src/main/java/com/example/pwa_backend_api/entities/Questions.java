package com.example.pwa_backend_api.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "QUESTION")
public class Questions {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(name = "title")
  private String title;

  @Column(name = "sub_title")
  private String subtitle;

  @Column(name = "hint")
  private String hint;

  @Column(name = "answer_type")
  private String answer_type;

  @ManyToOne()
  @JoinColumn(name = "forms_id", nullable = false)
  private Forms forms;

  @Column(name = "answers")
  @OneToMany(mappedBy = "question")
  private Set<Answer> answers = new HashSet<Answer>();

  @Column(name = "created_at")
  private Timestamp created_at;
  
  @Column(name = "updated_at")
  private Timestamp updated_at;
}
