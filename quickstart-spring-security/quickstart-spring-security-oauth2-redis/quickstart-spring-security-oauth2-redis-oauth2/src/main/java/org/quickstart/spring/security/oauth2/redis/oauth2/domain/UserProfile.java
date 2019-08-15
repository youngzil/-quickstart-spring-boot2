package org.quickstart.spring.security.oauth2.redis.oauth2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {
  private String username;
  private String email;

}
