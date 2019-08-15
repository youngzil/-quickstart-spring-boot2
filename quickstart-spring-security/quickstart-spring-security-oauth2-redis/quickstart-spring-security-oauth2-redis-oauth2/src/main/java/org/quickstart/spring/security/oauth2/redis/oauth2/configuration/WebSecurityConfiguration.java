package org.quickstart.spring.security.oauth2.redis.oauth2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // http.formLogin().loginPage("/singin.html").loginProcessingUrl("/authentication/form").and().authorizeRequests() // 授权配置
    // .antMatchers("/singin.html").permitAll().and().csrf().disable();

    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//
        .and().authorizeRequests()//
        .antMatchers("/user", "/client", "/login.html").permitAll()// 授权配置
        .anyRequest().authenticated()// 所有请求必须登陆后访问
        .and().httpBasic()//
        .and().formLogin()// 默认配置，/login和/login?error
        // .loginPage("/login.html").loginProcessingUrl("/authentication/form").defaultSuccessUrl("/index")
        // .failureUrl("/loginError.html").permitAll()// 登录界面，错误界面可以直接访问
        .and().logout();//
        // .logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()// 注销请求可直接访问
        // .and().rememberMe();
  }

  /*
  @Autowired
  RedisConnectionFactory redisConnectionFactory;
  
  @Override
  @Bean
  public UserDetailsManager userDetailsService() {
  RedisUserDetailsService redisUserDetailsService = new RedisUserDetailsService();
  // redisUserDetailsService.setPasswordEncoder(passwordEncoder());
  redisUserDetailsService.setRedisConnectionFactory(redisConnectionFactory);
  
  try {
    redisUserDetailsService.setAuthenticationManager(super.authenticationManagerBean());
  } catch (Exception e) {
    e.printStackTrace();
  }
  
  return redisUserDetailsService;
  }*/

}
