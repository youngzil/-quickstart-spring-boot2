package org.quickstart.spring.security.oauth2.redis.client.domain;

import lombok.Data;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-14 18:06
 */
@Data
public class TokenInfo {
  private String accessToken;
  private String refreshToken;
  private String tokenType;
  private int expiresIn;
  private String scope;
}
