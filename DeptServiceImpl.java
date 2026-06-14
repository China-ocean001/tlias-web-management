package com.itheima.service.impl;

import com.itheima.mapper.DeptLogMapper;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;


    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list(){
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class)//spring事务管理
    @Override
    public void delete(Integer id) throws Exception {
        try {
            deptMapper.deleteById(id);//根据ID删除部门数据

//        if(true){
//            throw new Exception("出错了");
//        }

            empMapper.deleteByDeptId(id);//根据部门ID删除部门下的员工


        } finally {
            DeptLog deptLog =new DeptLog();
            deptLog.setOperation("delete");
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作,此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.add(dept);

        DeptLog deptLog =new DeptLog();
        deptLog.setOperation("insert");
        deptLog.setCreateTime(LocalDateTime.now());
        deptLog.setDescription("执行了添加部门的操作,此次添加的是"+dept.getId()+"号部门");
        deptLogService.insert(deptLog);
    }

    @Override
    public Dept listById(Integer id){

        return deptMapper.listById(id);
    }

    @Override
    public void update(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.updateById(dept);

        DeptLog deptLog =new DeptLog();
        deptLog.setOperation("update");
        deptLog.setCreateTime(LocalDateTime.now());
        deptLog.setDescription("执行了更新部门的操作,此次更新的是"+dept.getId()+"号部门");
        deptLogService.insert(deptLog);
    }
}
