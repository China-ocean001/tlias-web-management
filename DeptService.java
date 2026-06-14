package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    //查询全部部门成绩
    public List<Dept> list();

    //删除部门
    public void delete(Integer id) throws Exception;

    //新增部门
    public void add(Dept dept);

    //根据ID查询部门
    public Dept listById(Integer id);

    //修改部门
    public void update(Dept dept);
}
