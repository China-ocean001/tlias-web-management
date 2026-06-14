package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpLog;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

        //1.设置分页参数
        PageHelper.startPage(page, pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;

        //3.封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //1. 获取要删除的员工信息，用于日志记录
        for (Integer id : ids) {
            Emp emp = empMapper.getById(id);
            if (emp != null) {
                //2. 删除 login 表中的账号记录
                empMapper.deleteLoginByUsername(emp.getUsername());

                //3. 记录员工日志
                EmpLog empLog = new EmpLog();
                empLog.setOperation("delete");
                empLog.setCreateTime(LocalDateTime.now());
                empLog.setDescription("执行了删除员工的操作,此次删除的是" + emp.getName() + "(" + emp.getUsername() + ")");
                empLogService.insert(empLog);
            }
        }
        //4. 执行删除
        empMapper.delete(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        //1. 设置默认密码（需求要求默认密码为123456）
        if (emp.getPassword() == null || emp.getPassword().isEmpty()) {
            emp.setPassword(PasswordUtils.DEFAULT_PASSWORD);
        }

        //2. 先加密密码，再插入 login 表；emp 表不存密码明文
        String encodedPassword = PasswordUtils.encode(emp.getPassword());

        //3. 设置时间
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        //4. 插入员工记录（emp 表不含 password 字段，无需设置）
        empMapper.insert(emp);

        //5. 在 login 表中创建登录账号，使用加密后的密码
        emp.setPassword(encodedPassword);
        empMapper.insertLogin(emp);

        //6. 记录员工日志
        EmpLog empLog = new EmpLog();
        empLog.setOperation("insert");
        empLog.setCreateTime(LocalDateTime.now());
        empLog.setDescription("执行了添加员工的操作,此次添加的员工是" + emp.getName() + "(" + emp.getUsername() + ")");
        empLogService.insert(empLog);
    }

    //根据ID查询员工
    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        //更新员工信息
        empMapper.update(emp);

        //如果更新了密码，同步更新 login 表
        if (emp.getPassword() != null && !emp.getPassword().isEmpty()) {
            emp.setPassword(PasswordUtils.encode(emp.getPassword()));
            empMapper.updateLoginPassword(emp);
        }

        //记录员工日志
        EmpLog empLog = new EmpLog();
        empLog.setOperation("update");
        empLog.setCreateTime(LocalDateTime.now());
        empLog.setDescription("执行了更新员工的操作,此次更新的是" + emp.getId() + "号员工");
        empLogService.insert(empLog);
    }

    @Override
    public Emp login(Emp emp) {
        //将密码加密后比对
        emp.setPassword(PasswordUtils.encode(emp.getPassword()));
        return empMapper.getByUsernameAndPassword(emp);
    }

    @Override
    public void register(Emp emp) {
        //注册新用户——创建登录账号
        emp.setPassword(PasswordUtils.encode(emp.getPassword()));
        empMapper.insertLogin(emp);
    }
}
