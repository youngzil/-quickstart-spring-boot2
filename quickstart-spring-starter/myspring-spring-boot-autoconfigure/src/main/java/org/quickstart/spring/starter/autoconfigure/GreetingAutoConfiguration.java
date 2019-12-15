package org.quickstart.spring.starter.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/24 11:37
 */
@Configuration
@ConditionalOnProperty(value = "quickstart.spring.starter.enable", havingValue = "true")
@ConditionalOnClass(QuickstartSpringStarter.class)
public class GreetingAutoConfiguration {

  @Bean
  public GreetingProperties greetingProperties() {
    return new GreetingProperties();
  }

  @Bean
  public GreetingService greetingService(GreetingProperties greetingProperties) {
    return new GreetingService(greetingProperties.getMembers());
  }

}
