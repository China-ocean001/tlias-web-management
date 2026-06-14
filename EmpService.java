package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    //查询全部员工信息
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除操作
    void delete(List<Integer> ids);

    //新增员工
    void save(Emp emp);

    //根据ID查询员工
    Emp getById(Integer id);

    //更新员工
    void update(Emp emp);

    //员工登录
    Emp login(Emp emp);

    //注册新用户（管理员创建登录账号）
    void register(Emp emp);
}



