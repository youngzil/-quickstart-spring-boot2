package com.quickstart.springboot.swagger.domain;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/15 22:06
 */
public class User {

  private Long id;
  private String name;
  private Integer age;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}