package com.quickstart.springboot.admin.client.configuer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author dax
 * @since 2019/10/20 0:13
 */
@Configuration
public class ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        // 禁用csrf
        http.csrf().ignoringAntMatchers("/actuator/**");
    }
}
