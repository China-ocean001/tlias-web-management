package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //员工信息的查询
    //@Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除
    void delete(List<Integer> ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Emp emp);

    //根据ID查询员工
    Emp getById(Integer id);

    //更新员工
    void update(Emp emp);

    //根据用户名和密码查询员工（密码加密后比对）
    @Select("select e.* from emp e inner join login l on e.username = l.account where l.account = #{username} and l.password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    //根据部门的ID删除该部门下的员工数据
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);

    //在 login 表中插入登录账号（密码加密存储）
    @Insert("insert into login(account, password) values(#{username}, #{password})")
    void insertLogin(Emp emp);

    //根据用户名删除 login 表中的记录
    @Delete("delete from login where account = #{username}")
    void deleteLoginByUsername(String username);

    //更新 login 表中的密码
    @Update("update login set password = #{password} where account = #{username}")
    void updateLoginPassword(Emp emp);
}
