package org.quickstart.spring.security.oauth2.redis.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;

@Component
public class RedisClientDetailsServiceBuilder extends ClientDetailsServiceBuilder<RedisClientDetailsServiceBuilder> {

  @Autowired
  private RedisClientDetailsService redisClientDetailsService;

  @Override
  protected void addClient(final String clientId, final ClientDetails build) {
    redisClientDetailsService.storeClientDetails(build);
  }

  @Override
  protected ClientDetailsService performBuild() {
    return redisClientDetailsService;
  }

}
