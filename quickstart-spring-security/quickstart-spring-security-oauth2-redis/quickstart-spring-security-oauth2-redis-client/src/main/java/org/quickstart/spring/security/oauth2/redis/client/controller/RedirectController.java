package org.quickstart.spring.security.oauth2.redis.client.controller;

import com.alibaba.fastjson.JSON;
import org.quickstart.spring.security.oauth2.redis.client.domain.TokenInfo;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RedirectController {

  private TokenInfo tokenInfo;

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/user")
  public Principal user(Principal principal) {
    return principal;
  }

  @GetMapping("/redirect")
  @ResponseBody
  public String callback(@RequestParam String code) {
    log.info("receive code {}", code);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("code", code);
    params.add("client_id", "resourceServer1");
    params.add("client_secret", "123456");
    params.add("redirect_uri", "http://localhost:9002/oauth2-client/redirect");
    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9001/oauth2-server/oauth/token", requestEntity, String.class);
    String token = response.getBody();
    log.info("token => {}", token);

    tokenInfo = JSON.parseObject(token, TokenInfo.class);

    return token;

  }

  @GetMapping("/check_token")
  @ResponseBody
  public String checkToken() {

    String url = "http://localhost:9001/oauth2-server/oauth/check_token?token=" + tokenInfo.getAccessToken();
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    String token = response.getBody();
    log.info("checkToken => {}", token);
    return token;

  }

  @GetMapping("/refresh_token")
  @ResponseBody
  public String refreshToken() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "refresh_token");
    params.add("refresh_token", tokenInfo.getRefreshToken());
    params.add("client_id", "resourceServer1");
    params.add("client_secret", "123456");

    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9001/oauth2-server/oauth/token", requestEntity, String.class);
    String token = response.getBody();
    log.info("refreshToken => {}", token);
    return token;

  }

  @GetMapping("/client_credentials")
  @ResponseBody
  public String clientCredentials() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "client_credentials");
    params.add("client_id", "resourceServer1");
    params.add("client_secret", "123456");
    // params.add("redirect_uri", "http://localhost:9002/oauth2-client/redirect");

    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
    ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9001/oauth2-server/oauth/token", requestEntity, String.class);
    String token = response.getBody();
    log.info("client_credentials_Token => {}", token);
    return token;

  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
