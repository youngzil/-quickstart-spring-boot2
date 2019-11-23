/*
 * Copyright 2017-2019 Pamarin.com
 */
package com.example.webflux.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jitta
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

  private String username;

  private String password;

}
