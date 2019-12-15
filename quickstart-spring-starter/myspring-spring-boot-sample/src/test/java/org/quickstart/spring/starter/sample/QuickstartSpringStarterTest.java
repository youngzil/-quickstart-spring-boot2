package org.quickstart.spring.starter.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quickstart.spring.starter.autoconfigure.GreetingService;
import org.quickstart.spring.starter.autoconfigure.QuickstartSpringStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/24 12:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuickstartSpringStarterTest {

  @Autowired(required = false)
  private GreetingService greetingService;

  // @Autowired(required = false)
  // private QuickstartSpringStarter quickstartSpringStarter;

  @Test
  public void testGreeting() {
    greetingService.sayHello();
  }

}
