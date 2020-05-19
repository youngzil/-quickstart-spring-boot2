package org.quickstart.spring.security.oauth2.redis.oauth2;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;
import org.springframework.security.oauth2.common.util.OAuth2Utils;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2020/5/19 16:16
 */
public  class OAuth2AuthToAccessTest {

  private static final String CLIENT_ID = "client_id";
  private static final String SCOPE = "scope";
  private static final String USERNAME = "username";

  @Test
  public void testAuthToAccessKey() {

    Map<String, String> values = new LinkedHashMap<String, String>();
    values.put(CLIENT_ID, "app_1587020196405");

    Set<String> scope = new HashSet<>();
    scope.add("all");
    values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<String>(scope)));

    String key = generateKey(values);
    System.out.println("key=" + key);

  }

  protected String generateKey(Map<String, String> values) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("MD5");
      byte[] bytes = digest.digest(values.toString().getBytes("UTF-8"));
      return String.format("%032x", new BigInteger(1, bytes));
    } catch (NoSuchAlgorithmException nsae) {
      throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", nsae);
    } catch (UnsupportedEncodingException uee) {
      throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).", uee);
    }
  }

}