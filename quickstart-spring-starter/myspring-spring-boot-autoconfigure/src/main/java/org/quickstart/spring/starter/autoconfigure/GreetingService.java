package org.quickstart.spring.starter.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/11/24 11:37
 */
@AllArgsConstructor
public class GreetingService {

  private List<String> members = new ArrayList<>();

  public void sayHello(){
    members.forEach(s -> System.out.println("hello " + s));
  }

}