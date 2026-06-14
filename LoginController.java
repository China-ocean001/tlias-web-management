package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import com.itheima.utils.PasswordUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录:{}", emp);
        Emp e = empService.login(emp);

        //登录成功，生成令牌，下发令牌
        if (e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);//jwt包含了当前登录的员工信息

            return Result.success(jwt);
        }

        //登陆失败，返回错误信息
        return Result.error("用户名或密码错误");
    }

    /**
     * 用户注册接口（系统管理员创建新用户账号）
     * 前置条件：系统管理员已登录
     * 后置条件：新用户账号成功创建
     */
    @PostMapping("/register")
    public Result register(@RequestBody Emp emp){
        log.info("注册新用户: {}", emp);

        //参数校验
        if (emp.getUsername() == null || emp.getUsername().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (emp.getPassword() == null || emp.getPassword().isEmpty()) {
            //使用默认密码
            emp.setPassword(PasswordUtils.DEFAULT_PASSWORD);
        }

        try {
            empService.register(emp);
            log.info("用户注册成功: {}", emp.getUsername());
            return Result.success();
        } catch (Exception e) {
            log.error("用户注册失败", e);
            return Result.error("注册失败: " + e.getMessage());
        }
    }
}
