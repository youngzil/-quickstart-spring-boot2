package org.quickstart.spring.security.oauth2.redis.oauth2.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-13 14:02
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Override
  public boolean supports(Class<? extends Object> arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @SuppressWarnings("serial")
  private static Map<String, String> SIMPLE_USERS = new HashMap<String, String>(2) {
    {
      put("joe", "joe");
      put("bob", "bob");
    }
  };

  @SuppressWarnings("serial")
  private static List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>(1) {
    // {
    // add(new GrantedAuthorityImpl("ROLE_USER"));
    // }
  };

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    // All your user authentication needs
    System.out.println("==Authenticate Me==");
    if (SIMPLE_USERS.containsKey(auth.getPrincipal()) && SIMPLE_USERS.get(auth.getPrincipal()).equals(auth.getCredentials())) {
      return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
    }
    throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
  }

}
