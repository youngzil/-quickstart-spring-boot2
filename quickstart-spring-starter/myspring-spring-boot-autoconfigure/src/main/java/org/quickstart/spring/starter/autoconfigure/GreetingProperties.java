package org.quickstart.spring.starter.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/24 11:38
 */
@Data
@Component
@ConfigurationProperties(prefix = "quickstart.spring.starter")
public class GreetingProperties {

  /**
   * GreetingProperties 开关
   */
  boolean enable = false;

  /**
   * 需要打招呼的成员列表
   */
  List<String> members = new ArrayList<>();

}


