package org.quickstart.spring.security.oauth2.redis.client.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//
        .and().authorizeRequests()//
        .antMatchers("client_credentials","/refresh_token","/check_token","/redirect", "/user", "/error**", "/**/favicon.ico").permitAll()//
        .anyRequest().authenticated()//
        .and().logout()//
        .and().csrf().disable();
  }
}
