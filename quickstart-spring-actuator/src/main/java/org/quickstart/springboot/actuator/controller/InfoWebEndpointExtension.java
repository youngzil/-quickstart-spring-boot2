package org.quickstart.springboot.actuator.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/9/9 00:09
 */
@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class InfoWebEndpointExtension {

  private InfoEndpoint delegate;

  @Autowired
  public InfoWebEndpointExtension(InfoEndpoint delegate) {
    this.delegate = delegate;
  }

  @ReadOperation
  public WebEndpointResponse<Map> info() {
    Map<String, Object> info = this.delegate.info();
    Integer status = getStatus(info);
    return new WebEndpointResponse<>(info, status);
  }

  private Integer getStatus(Map<String, Object> info) {
    // return 5xx if this is a snapshot
    return 200;
  }
}
