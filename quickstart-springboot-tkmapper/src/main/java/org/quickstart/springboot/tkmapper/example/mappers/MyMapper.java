package org.quickstart.springboot.tkmapper.example.mappers;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/9/14 09:49
 */
/*
        Mapper接口：基本的增、删、改、查方法
        MySqlMapper：针对MySQL的额外补充接口，支持批量插入*/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
