package com.quickstart.springboot.actuator.controller;

import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/9/9 00:08
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class))
        .hasRole("ACTUATOR_ADMIN")
        .requestMatchers(EndpointRequest.toAnyEndpoint())
        .permitAll()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        .permitAll()
        .antMatchers("/")
        .permitAll()
        .antMatchers("/**")
        .authenticated()
        .and()
        .httpBasic();
  }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
                .anyRequest().permitAll();
    }*/
}
