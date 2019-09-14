package org.quickstart.springboot.tkmapper.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/14 09:41
 */


/*

@MapperScan仅扫描业务接口包，不能扫描本地通用Mapper接口包，
  否则报java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.TypeVariableImpl
        cannot be cast to java.lang.Class异常
*/
@SpringBootApplication
@MapperScan(basePackages = {"yiche.com.mapper"})
public class MapperApplication {
  public static void main(String[] args){

    SpringApplication.run(MapperApplication.class,args);
  }
}
