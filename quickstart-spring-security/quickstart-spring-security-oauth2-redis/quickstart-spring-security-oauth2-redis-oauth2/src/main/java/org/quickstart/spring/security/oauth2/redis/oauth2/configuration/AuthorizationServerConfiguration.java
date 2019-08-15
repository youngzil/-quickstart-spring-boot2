package org.quickstart.spring.security.oauth2.redis.oauth2.configuration;

import org.quickstart.spring.security.oauth2.redis.oauth2.service.RedisClientDetailsServiceBuilder;
import org.quickstart.spring.security.oauth2.redis.oauth2.service.RedisUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
@Order(2)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private RedisUserDetailsService redisUserDetailsService;

  @Autowired
  private RedisClientDetailsServiceBuilder redisClientDetailsServiceBuilder;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.tokenStore(tokenStore)//
        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)//
        .authenticationManager(authenticationManager)//
        .userDetailsService(redisUserDetailsService);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // 使用自己的redis来存放client的认证数据
    clients.setBuilder(redisClientDetailsServiceBuilder);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.allowFormAuthenticationForClients()// 允许from表单提交
        .checkTokenAccess("authenticated") // 具有什么权限的才会反问接口
        .tokenKeyAccess("authenticated");
  }

  /**
   * 配置AccessToken的存储方式：此处使用Redis存储 Token的可选存储方式 1、InMemoryTokenStore 2、JdbcTokenStore 3、JwtTokenStore 4、RedisTokenStore 5、JwkTokenStore
   */
  @Bean
  public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
    RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
    redisTokenStore.setPrefix("oauth2_");
    return redisTokenStore;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
