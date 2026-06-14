# 🏢 Tlias 部门员工管理系统

> Spring Boot + MyBatis + JWT 企业级后台管理 REST API

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MyBatis](https://img.shields.io/badge/MyBatis-3.x-red.svg)](https://mybatis.org/)
[![JWT](https://img.shields.io/badge/JWT-认证-black.svg)](https://jwt.io/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## 📖 项目简介

基于 Spring Boot 的企业级部门员工管理系统，提供完整的 RESTful API 后端服务。涵盖 CRUD 操作、JWT 认证、AOP 日志、阿里云 OSS 上传等功能，附带 Vue 前端页面。

## ✨ 功能特性

- 🏢 **部门管理**：部门 CRUD + 操作日志
- 👨‍💼 **员工管理**：员工 CRUD + 操作日志
- 🔐 **JWT 认证**：无状态 Token 鉴权
- 🔍 **日志审计**：AOP 切面自动记录操作日志
- 📤 **文件上传**：阿里云 OSS 对象存储
- 🛡️ **安全防护**：Filter + Interceptor 双层拦截
- 📄 **分页查询**：PageBean 统一分页封装
- 🎨 **前端页面**：4 个 Vue 组件页面

## 🛠 技术栈

| 层级 | 技术 |
|------|------|
| **框架** | Spring Boot 3.x |
| **ORM** | MyBatis + XML Mapper |
| **认证** | JWT (jjwt) |
| **日志** | Spring AOP |
| **存储** | 阿里云 OSS |
| **前端** | Vue 3 |
| **构建** | Maven |

## 📁 项目结构

```
tlias-web-management/
├── TliasWebManagementApplication.java   # 启动类
├── controller/
│   ├── DeptController.java              # 部门接口
│   ├── EmpController.java               # 员工接口
│   ├── LoginController.java             # 登录接口
│   ├── UploadController.java            # 文件上传接口
│   └── OperateLogController.java        # 操作日志接口
├── service/
│   ├── DeptService.java                 # 部门业务
│   ├── EmpService.java                  # 员工业务
│   └── impl/                            # 实现类
├── mapper/
│   ├── DeptMapper.java                  # 部门 Mapper
│   ├── EmpMapper.java                   # 员工 Mapper
│   └── OperateLogMapper.java            # 日志 Mapper
├── entity/
│   ├── Dept.java, Emp.java              # 实体类
│   ├── DeptLog.java, EmpLog.java        # 日志实体
│   └── OperateLog.java                  # 操作日志
├── filter/
│   ├── LoginCheckFilter.java            # 登录检查过滤器
│   └── DemoFilterr.java                 # 示例过滤器
├── interceptor/
│   └── LoginCheckInterceptor.java       # 登录拦截器
├── utils/
│   ├── JwtUtils.java                    # JWT 工具类
│   ├── AliOSSUtils.java                 # 阿里云 OSS 工具
│   ├── PasswordUtils.java               # 密码工具
│   └── AliOSSProperties.java            # OSS 配置
├── aop/
│   └── LogAspect.java                   # AOP 日志切面
├── common/
│   ├── Result.java                      # 统一响应
│   ├── PageBean.java                    # 分页封装
│   └── GlobalExceptionHandler.java      # 全局异常处理
├── config/
│   └── WebConfig.java                   # Web 配置
└── index(1-4).vue                       # Vue 前端页面
```

## 🚀 快速开始

```bash
# 配置阿里云 OSS (可选，如不需要上传功能可注释)
# 修改 AliOSSProperties.java 中的 accessKey

# 启动
mvn spring-boot:run
```

## 📡 API 接口

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/login` | 用户登录，返回 JWT |
| GET | `/depts` | 分页查询部门 |
| POST | `/depts` | 新增部门 |
| PUT | `/depts/{id}` | 修改部门 |
| DELETE | `/depts/{id}` | 删除部门 |
| GET | `/emps` | 分页查询员工 |
| POST | `/emps` | 新增员工 |
| PUT | `/emps/{id}` | 修改员工 |
| DELETE | `/emps/{id}` | 删除员工 |
| POST | `/upload` | 文件上传到 OSS |
| GET | `/operateLogs` | 查询操作日志 |

## 📄 License

MIT License — 仅供学习交流使用
