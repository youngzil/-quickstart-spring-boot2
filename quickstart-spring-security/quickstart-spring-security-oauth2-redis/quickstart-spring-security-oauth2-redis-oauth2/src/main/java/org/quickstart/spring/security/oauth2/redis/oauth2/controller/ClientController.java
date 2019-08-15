package org.quickstart.spring.security.oauth2.redis.oauth2.controller;

import org.quickstart.spring.security.oauth2.redis.oauth2.service.RedisClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/client")
@RestController
public class ClientController {

  @Autowired
  RedisClientDetailsService redisClientDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping(value = {"", "/", "/add"})
  ResponseEntity addClient(@RequestBody BaseClientDetails clientDetails) {
    clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
    redisClientDetailsService.storeClientDetails(clientDetails);
    return ResponseEntity.ok().build();
  }
}
