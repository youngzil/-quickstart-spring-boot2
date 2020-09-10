package com.quickstart.springboot.netty.action.common.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/9/9 00:29
 */
@Component
public class PrometheusCustomMonitor {

  /**
   * 记录请求出错次数
   */
  private Counter requestErrorCount;

  /**
   * 订单发起次数
   */
  private Counter orderCount;

  /**
   * 金额统计
   */
  private DistributionSummary amountSum;

  private final MeterRegistry registry;

  @Autowired
  public PrometheusCustomMonitor(MeterRegistry registry) {
    this.registry = registry;
  }

  @PostConstruct
  private void init() {
    requestErrorCount = registry.counter("requests_error_total", "status", "error");
    orderCount = registry.counter("order_request_count", "order", "test-svc");
    amountSum = registry.summary("order_amount_sum", "orderAmount", "test-svc");
  }

  public Counter getRequestErrorCount() {
    return requestErrorCount;
  }

  public Counter getOrderCount() {
    return orderCount;
  }

  public DistributionSummary getAmountSum() {
    return amountSum;
  }
}
