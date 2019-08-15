package org.quickstart.spring.security.oauth2.redis.oauth2.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.quickstart.spring.security.oauth2.redis.oauth2.service.RedisUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private RedisUserDetailsService redisUserDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @RequestMapping(path = {"/me"}, method = {RequestMethod.GET})
  Principal me(Principal principal) {
    return principal;
  }

  @RequestMapping(path = "", method = {RequestMethod.POST})
  ResponseEntity user(@RequestBody UserInfo user) {
    UserBuilder userBuilder = User.builder();
    userBuilder.passwordEncoder(passwordEncoder::encode);
    userBuilder.username(user.getName());
    userBuilder.authorities(user.roles.toArray(new String[]{}));
    userBuilder.password(user.getPassword());
    redisUserDetailsService.createUser(userBuilder.build());
    return ResponseEntity.ok().build();
  }

  @Getter
  @Setter
  public static class UserInfo {

    private String name;
    private String password;
    private List<String> roles = new ArrayList<>();
  }

}
