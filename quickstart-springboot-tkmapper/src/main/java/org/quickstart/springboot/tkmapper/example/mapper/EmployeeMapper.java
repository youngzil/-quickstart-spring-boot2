package org.quickstart.springboot.tkmapper.example.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.quickstart.springboot.tkmapper.example.entity.Employee;
import org.quickstart.springboot.tkmapper.example.mappers.MyMapper;
import org.springframework.stereotype.Component;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/9/14 09:49
 */
@Mapper
@Component
public interface EmployeeMapper extends MyMapper<Employee> {

  //在使用通用Mapper的基础上 下面的都是mybatis注解的方法
  @Select("select * from employee")
  List<Employee> getAll();
}
