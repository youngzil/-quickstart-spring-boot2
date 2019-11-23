package com.example.webflux.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  private String id;
  private String firstname;
  private String lastname;
  private int age;

  private String username;

  private String password;

  private String email;

  public User(String id, String firstname, String lastname, int age) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.age = age;
  }

}