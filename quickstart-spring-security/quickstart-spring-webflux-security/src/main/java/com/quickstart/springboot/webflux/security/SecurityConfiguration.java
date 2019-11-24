package com.quickstart.springboot.webflux.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    LOGGER.debug("Initializing the security configuration");
    return http.authorizeExchange()
        .pathMatchers("/private").hasRole("USER")
        .matchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
        .anyExchange().permitAll()
        .and().httpBasic()
        .and().build();
  }

  /*@Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }*/

  /**
   * Sample in-memory user details service.
   */
  @SuppressWarnings("deprecation") // Removes warning from "withDefaultPasswordEncoder()"
  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    LOGGER.debug("Initializing the user details service");
        return new MapReactiveUserDetailsService(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build(),
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                    // .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")
                        .roles("USER,ADMIN")
                        .build());
    /*return new MapReactiveUserDetailsService(
        User.withUsername("user")
            .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")
            .roles("USER")
            .build());*/

  }

}
