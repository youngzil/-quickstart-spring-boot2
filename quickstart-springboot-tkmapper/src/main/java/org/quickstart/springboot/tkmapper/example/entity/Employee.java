package org.quickstart.springboot.tkmapper.example.entity;

import java.security.PrivateKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/9/14 09:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

  private Integer id;
  private String name;
  private  double salary;
  private int age;

  public Employee(String name, double salary, int age) {
    this.name = name;
    this.salary = salary;
    this.age = age;

  }
}
