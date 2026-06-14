package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询全部部门

    @Select("select * from dept")
    public List<Dept> list();

    //删除部门
    @Delete("delete from dept where id = #{id}")
    public void deleteById(Integer id);

    //新增部门
    @Insert("insert into dept (name,create_time,update_time) value (#{name},#{createTime},#{updateTime})")
    public void add(Dept dept);

    @Select("select * from dept where id = #{id}")
    public Dept listById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    public void updateById(Dept dept);
}
