package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 员工日志实体类
 * 每次对 Emp 表的操作（插入、更新、删除）都会在 EmpLog 表中记录一条日志
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLog {
    private Integer id; //日志唯一标识符（主键，自动增长）
    private String operation; //操作类型: insert / update / delete
    private LocalDateTime createTime; //操作时间
    private String description; //操作描述
}
