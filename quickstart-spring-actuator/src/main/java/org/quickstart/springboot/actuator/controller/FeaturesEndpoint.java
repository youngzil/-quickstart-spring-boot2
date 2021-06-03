package org.quickstart.springboot.actuator.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/9/9 00:10
 */
@Component
@Endpoint(id = "features")
public class FeaturesEndpoint {

  @Resource
  private PrometheusCustomMonitor monitor;

  private Map<String, Feature> features = new ConcurrentHashMap<>();

  @ReadOperation
  public Map<String, Feature> features() {
    return features;
  }

  @ReadOperation
  public Feature feature(@Selector String name) {
    return features.get(name);
  }

  @WriteOperation
  public void configureFeature(@Selector String name, Feature feature) {
    features.put(name, feature);
  }

  @DeleteOperation
  public void deleteFeature(@Selector String name) {
    features.remove(name);
  }

  public static class Feature {

    private Boolean enabled;

    public Boolean getEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }
  }

}
