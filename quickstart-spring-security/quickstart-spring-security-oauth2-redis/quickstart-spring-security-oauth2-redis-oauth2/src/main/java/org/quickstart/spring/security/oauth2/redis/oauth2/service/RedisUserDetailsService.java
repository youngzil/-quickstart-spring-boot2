package org.quickstart.spring.security.oauth2.redis.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Component
@Slf4j
public class RedisUserDetailsService implements UserDetailsManager, UserDetailsPasswordService {

  private static final String USERNAME_KEY = "username:";

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    RedisConnection conn = getConnection();
    try {
      byte[] key = RedisSerialiseUtil.serializeKey(USERNAME_KEY, username);
      byte[] value = conn.get(key);
      UserDetails user = RedisSerialiseUtil.deserialize(value, UserDetails.class);
      return user;
    } finally {
      conn.close();
    }

  }

  @Override
  public void createUser(UserDetails user) {
    Assert.isTrue(!userExists(user.getUsername()), "user should not exist");
    saveOrUpdate(user);
  }

  private void saveOrUpdate(UserDetails user) {
    RedisConnection conn = getConnection();
    try {
      byte[] key = RedisSerialiseUtil.serializeKey(USERNAME_KEY, user.getUsername());
      byte[] value = RedisSerialiseUtil.serialize(user);
      conn.set(key, value);
    } finally {
      conn.close();
    }
  }

  @Override
  public void updateUser(UserDetails user) {
    Assert.isTrue(userExists(user.getUsername()), "user should exist");
    saveOrUpdate(user);
  }

  @Override
  public void deleteUser(String username) {
    RedisConnection conn = getConnection();

    try {
      byte[] key = RedisSerialiseUtil.serializeKey(USERNAME_KEY, username);
      Long count = conn.del(key);
      log.info("deleteUser {} is {}", username, Boolean.valueOf(count > 0).toString());
    } finally {
      conn.close();
    }
  }

  @Override
  public boolean userExists(String username) {
    return loadUserByUsername(username) != null;
  }

  @Override
  public UserDetails updatePassword(UserDetails user, String newPassword) {
    return null;
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
    Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

    if (currentUser == null) {
      // This would indicate bad coding somewhere
      throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
    }

    String username = currentUser.getName();

    log.debug("Changing password for user '" + username + "'");

    // If an authentication manager has been set, re-authenticate the user with the
    // supplied password.
    if (authenticationManager != null) {
      log.debug("Reauthenticating user '" + username + "' for password change request.");

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
    } else {
      log.debug("No authentication manager set. Password won't be re-checked.");
    }

    RedisConnection conn = getConnection();

    try {
      UserDetails userDetails = loadUserByUsername(username);

      // // 一些验证
      // if (!userDetails.isAccountNonExpired()) {
      // throw new AccessDeniedException("账户已失效");
      // }
      //
      // if (!userDetails.isAccountNonLocked()) {
      // throw new AccessDeniedException("账户已失效");
      // }
      //
      // if (oldPassword != null && oldPassword.equals(newPassword)) {
      // throw new AccessDeniedException("新老密码不能一样");
      // }

      User.UserBuilder userBuilder = User.withUserDetails(userDetails).password(newPassword);
      userBuilder.passwordEncoder(passwordEncoder::encode);

      saveOrUpdate(userBuilder.build());
    } finally {
      conn.close();
    }
  }

  private RedisConnection getConnection() {
    return this.redisConnectionFactory.getConnection();
  }

  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public void setRedisConnectionFactory(RedisConnectionFactory redisConnectionFactory) {
    this.redisConnectionFactory = redisConnectionFactory;
  }

}
