package org.quickstart.spring.security.oauth2.redis.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by wusir on 2019/7/11.
 */
@EnableAuthorizationServer
@SpringBootApplication
public class OAuth2AuthorizationServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
  }

}
