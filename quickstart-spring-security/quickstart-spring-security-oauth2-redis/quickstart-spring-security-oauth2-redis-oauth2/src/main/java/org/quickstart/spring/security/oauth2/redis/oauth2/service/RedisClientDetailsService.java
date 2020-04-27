package org.quickstart.spring.security.oauth2.redis.oauth2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Slf4j
public class RedisClientDetailsService implements ClientDetailsService {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;
  private String prefix = "";
  private static final String CLIENT_KEY = "client:";

  @Override
  public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {

    byte[] serializedClientId = RedisSerialiseUtil.serializeKey(prefix, CLIENT_KEY + clientId);

    byte[] bytes = null;
    RedisConnection conn = getConnection();
    try {
      bytes = conn.get(serializedClientId);
    } finally {
      conn.close();
    }
    final ClientDetails details = RedisSerialiseUtil.deserialize(bytes, ClientDetails.class);

    if (details == null) {
      throw new NoSuchClientException("not found,clientId=" + clientId);
    }

    return details;
  }

  public void storeClientDetails(ClientDetails clientDetails) {
    Assert.isTrue(clientExists(clientDetails.getClientId()), "client should not exist");
    saveOrUpdate(clientDetails);
  }

  public void updateClientDetails(ClientDetails clientDetails) {
    Assert.isTrue(!clientExists(clientDetails.getClientId()), "client should exist");
    saveOrUpdate(clientDetails);
  }

  private void saveOrUpdate(ClientDetails clientDetails) {
    RedisConnection conn = getConnection();
    try {
      byte[] key = RedisSerialiseUtil.serializeKey(prefix, CLIENT_KEY + clientDetails.getClientId());
      byte[] value = RedisSerialiseUtil.serialize(clientDetails);

      // 命令多的话，可以考虑开始管道来批量发送redis命令
      // conn.openPipeline();
      conn.set(key, value);
      // conn.closePipeline();
    } finally {
      conn.close();
    }
  }

  public boolean clientExists(final String clientId) {

    byte[] serializedClientId = RedisSerialiseUtil.serializeKey(prefix, CLIENT_KEY + clientId);

    byte[] bytes = null;
    RedisConnection conn = getConnection();
    try {
      bytes = conn.get(serializedClientId);
    } finally {
      conn.close();
    }
    final ClientDetails details = RedisSerialiseUtil.deserialize(bytes, ClientDetails.class);

    return details != null;
  }

  public void deleteClientDetails(final String clientId) {

    RedisConnection conn = getConnection();
    try {
      // 命令多的话，可以考虑开始管道来批量发送redis命令
      // conn.openPipeline();
      byte[] serializedClientId = RedisSerialiseUtil.serializeKey(prefix, CLIENT_KEY + clientId);
      Long count = conn.del(serializedClientId);

      log.info("deleteClientDetails {} is {}", clientId, Boolean.valueOf(count > 0).toString());
      // conn.closePipeline();
    } finally {
      conn.close();
    }
  }

  private RedisConnection getConnection() {
    return redisConnectionFactory.getConnection();
  }

}
