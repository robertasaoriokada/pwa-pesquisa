package com.example.pwa_backend_api.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements UserDetails{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "password")
  private String password;

  @Column(name = "forms")
  @OneToMany(mappedBy = "user")
  private Set<Forms> forms;

  @Column(name = "created_at")
  private Timestamp created_at;

  @Column(name = "updated_at")
  private Timestamp updated_at;

  @Column(name = "role")
  private UserRole role;


  User(){
  }

  public User(String name, String email, String password){
    this.name = name;
    this.email = email;
    this.password = password;
    this.forms = null;
    this.role = UserRole.USER;
    this.created_at = new Timestamp(Calendar.getInstance().getTimeInMillis());
    this.updated_at = new Timestamp(Calendar.getInstance().getTimeInMillis());
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public Set<Forms> getForms() {
    return forms;
  }

  public void setForms(Set<Forms> forms) {
    this.forms = forms;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Timestamp getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Timestamp created_at) {
    this.created_at = created_at;
  }

  public Timestamp getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Timestamp updated_at) {
    this.updated_at = updated_at;
  }

   @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      if (role == UserRole.ADMIN) {
          return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
      } else {
          return List.of(new SimpleGrantedAuthority("ROLE_USER"));
      }
  }

  @Override
  public String getUsername() {
      return email;
  }
}

