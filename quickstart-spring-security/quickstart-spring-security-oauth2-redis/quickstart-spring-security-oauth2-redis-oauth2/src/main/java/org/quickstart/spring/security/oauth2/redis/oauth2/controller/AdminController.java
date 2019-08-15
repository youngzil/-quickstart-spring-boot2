package org.quickstart.spring.security.oauth2.redis.oauth2.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.quickstart.spring.security.oauth2.redis.oauth2.domain.UserProfile;

@Controller
@RequestMapping("/api")
public class AdminController {

  @RequestMapping("/users")
  public ResponseEntity<List<UserProfile>> getAllUsers() {
    return ResponseEntity.ok(getUsers());
  }

  private List<UserProfile> getUsers() {
    List<UserProfile> users = new ArrayList<>();
    users.add(new UserProfile("adolfo", "adolfo@mailinator.com"));
    users.add(new UserProfile("demigreite", "demigreite@mailinator.com"));
    users.add(new UserProfile("jujuba", "jujuba@mailinator.com"));
    return users;
  }

  @RequestMapping("/profile")
  public ResponseEntity<Object> hello() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ResponseEntity.ok(principal);
  }

}
