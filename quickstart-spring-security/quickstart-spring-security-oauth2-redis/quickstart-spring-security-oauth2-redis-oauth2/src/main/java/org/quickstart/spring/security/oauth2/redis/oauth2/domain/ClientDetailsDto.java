package org.quickstart.spring.security.oauth2.redis.oauth2.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
public class ClientDetailsDto implements Serializable {
  private String clientId;
  private Set<String> resourceIds;
  private boolean secretRequired;
  private String clientSecret;
  private boolean scoped;
  private Set<String> scope;
  private Set<String> authorizedGrantTypes;
  private Set<String> registeredRedirectUri;
  private Collection<GrantedAuthority> authorities;
  private Integer accessTokenValiditySeconds;
  private Integer refreshTokenValiditySeconds;
  private boolean autoApprove;
  private Map<String, Object> additionalInformation;
}
