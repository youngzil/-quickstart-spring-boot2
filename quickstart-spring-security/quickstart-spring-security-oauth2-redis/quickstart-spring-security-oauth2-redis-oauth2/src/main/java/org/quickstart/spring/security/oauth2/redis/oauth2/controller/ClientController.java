package org.quickstart.spring.security.oauth2.redis.oauth2.controller;

import com.alibaba.fastjson.JSON;
import org.quickstart.spring.security.oauth2.redis.oauth2.service.RedisClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.web.bind.annotation.PathVariable;
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

  @RequestMapping(path = "/get/{clientId}")
  ResponseEntity get(@PathVariable("clientId") String clientId) {
    ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(clientId);
    if (null == clientDetails)
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    return new ResponseEntity<String>(JSON.toJSONString(clientDetails), HttpStatus.OK);
  }

  @PostMapping(value = {"/add"})
  ResponseEntity add(@RequestBody BaseClientDetails clientDetails) {
    clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
    redisClientDetailsService.storeClientDetails(clientDetails);
    return ResponseEntity.ok().build();
  }

  @PostMapping(value = {"/update"})
  ResponseEntity update(@RequestBody BaseClientDetails clientDetails) {
    clientDetails.setClientSecret(passwordEncoder.encode(clientDetails.getClientSecret()));
    redisClientDetailsService.updateClientDetails(clientDetails);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(path = "/delete/{clientId}")
  ResponseEntity delete(@PathVariable("clientId") String clientId) {
    redisClientDetailsService.deleteClientDetails(clientId);
    return ResponseEntity.ok().build();
  }

}
