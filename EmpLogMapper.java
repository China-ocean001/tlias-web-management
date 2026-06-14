package com.itheima.mapper;

import com.itheima.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLogMapper {

    @Insert("insert into emp_log(operation,create_time,description) values(#{operation},#{createTime},#{description})")
    void insert(EmpLog log);
}
