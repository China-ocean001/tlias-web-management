package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        return Result.success();
    }

    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门，参数:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result listById(@PathVariable Integer id) {
        log.info("根据id查询部门,id:{}", id);
        Dept dept = deptService.listById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("根据id修改部门,dept:{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
