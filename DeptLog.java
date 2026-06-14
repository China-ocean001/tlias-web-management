package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private Integer id;
    private String operation; //操作类型: insert / update / delete
    private LocalDateTime createTime; //操作时间 (operation_time)
    private String description; //操作描述
}
