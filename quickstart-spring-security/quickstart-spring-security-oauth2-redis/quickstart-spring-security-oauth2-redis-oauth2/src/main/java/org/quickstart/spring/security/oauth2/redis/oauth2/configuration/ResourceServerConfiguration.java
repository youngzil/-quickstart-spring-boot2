package org.quickstart.spring.security.oauth2.redis.oauth2.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

// @Configuration
// @EnableResourceServer
// @Order(6)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override 
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated() 
            .and() 
            .requestMatchers().antMatchers("/api/**"); 
    } 
}