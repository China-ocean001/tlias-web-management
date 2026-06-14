# 🏢 Tlias 部门员工管理系统

> Spring Boot + MyBatis + JWT 企业级后台管理 REST API

<div align="center">

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=flat&logo=springboot)](https://spring.io/projects/spring-boot)
[![MyBatis](https://img.shields.io/badge/MyBatis-3.x-red?style=flat)](https://mybatis.org/)
[![JWT](https://img.shields.io/badge/JWT-Auth-black?style=flat&logo=jsonwebtokens)](https://jwt.io/)
[![AOP](https://img.shields.io/badge/AOP-Logging-orange?style=flat)](https://docs.spring.io/spring-framework/reference/core/aop.html)
[![AliOSS](https://img.shields.io/badge/Ali_OSS-Storage-FF6A00?style=flat)](https://www.aliyun.com/product/oss)
[![Vue](https://img.shields.io/badge/Vue-3.x-4FC08D?style=flat&logo=vuedotjs)](https://vuejs.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow?style=flat)](LICENSE)

</div>

---

## 📑 目录

- [📖 项目简介](#-项目简介)
- [🏗️ 系统架构](#️-系统架构)
- [✨ 功能特性](#-功能特性)
- [🛠 技术栈](#-技术栈)
- [📁 项目结构](#-项目结构)
- [📡 API 接口](#-api-接口)
- [🔐 安全设计](#-安全设计)
- [📝 日志系统](#-日志系统)
- [🚀 快速开始](#-快速开始)
- [🐛 常见问题](#-常见问题)

---

## 📖 项目简介

**Tlias** 是一个基于 Spring Boot 3.x 构建的企业级部门员工管理系统后端服务，提供完整的 RESTful API。项目涵盖 CRUD 操作、JWT 无状态认证、AOP 日志审计、阿里云 OSS 文件上传等企业开发常见技术点，附带 Vue 前端页面。

### 技术亮点

- 🏗️ **经典分层**：Controller → Service → Mapper 三层架构
- 🔐 **JWT 无状态认证**：Filter + Interceptor 双层拦截
- 📝 **AOP 日志审计**：自动记录所有增删改操作
- ☁️ **阿里云 OSS**：文件云存储
- 📄 **统一分页**：PageBean 分页封装
- 🎯 **统一响应**：Result 类封装所有返回

---

## 🏗️ 系统架构

```
客户端 (Vue 3 / 浏览器)
        │
        ▼ HTTP Request
┌───────────────────────────────────┐
│         Filter 层                  │
│  ┌─────────────────────────────┐  │
│  │   LoginCheckFilter          │  │  ← JWT Token 校验
│  │   (除登录外全部拦截)        │  │
│  └─────────────────────────────┘  │
├───────────────────────────────────┤
│         Interceptor 层             │
│  ┌─────────────────────────────┐  │
│  │   LoginCheckInterceptor     │  │  ← 二次拦截 + 日志记录
│  └─────────────────────────────┘  │
├───────────────────────────────────┤
│         Controller 层              │
│  ┌──────┐ ┌──────┐ ┌──────────┐  │
│  │ Dept │ │ Emp  │ │ Upload   │  │
│  │ 部门 │ │ 员工 │ │ 上传     │  │
│  └──────┘ └──────┘ └──────────┘  │
│  ┌──────┐ ┌──────┐               │
│  │Login │ │Log   │               │
│  │登录  │ │日志  │               │
│  └──────┘ └──────┘               │
├───────────────────────────────────┤
│         Service 层                 │
│  DeptService │ EmpService         │
│  DeptLogService │ EmpLogService   │
├───────────────────────────────────┤
│         Mapper 层 (MyBatis)        │
│  DeptMapper │ EmpMapper           │
│  DeptLogMapper │ EmpLogMapper     │
│  OperateLogMapper                 │
├───────────────────────────────────┤
│         数据库 (MySQL)             │
│  ┌────┐ ┌────┐ ┌──────┐ ┌──────┐ │
│  │Dept│ │Emp │ │Log表 │ │用户表│ │
│  └────┘ └────┘ └──────┘ └──────┘ │
└───────────────────────────────────┘
         │
         ▼ HTTP Response (JSON)
客户端接收统一格式响应
```

---

## ✨ 功能特性

### 🏢 部门管理
- ✅ 部门分页查询（模糊搜索）
- ✅ 新增部门
- ✅ 修改部门
- ✅ 删除部门（级联检查员工）
- ✅ 部门操作自动日志记录

### 👨‍💼 员工管理
- ✅ 员工分页查询（按姓名/部门/入职日期筛选）
- ✅ 新增员工（关联部门）
- ✅ 修改员工信息
- ✅ 删除员工
- ✅ 员工操作自动日志记录

### 🔐 认证授权
- ✅ JWT Token 签发与验证
- ✅ Filter 过滤器全局拦截
- ✅ Interceptor 拦截器补充
- ✅ 密码加密存储

### 📝 日志审计
- ✅ AOP 切面自动记录操作
- ✅ 操作日志持久化存储
- ✅ 操作日志查询接口

### 📤 文件上传
- ✅ 阿里云 OSS 对象存储
- ✅ 本地文件上传备选

---

## 🛠 技术栈

| 层级 | 技术 | 说明 |
|------|------|------|
| **框架** | Spring Boot 3.x | 基础框架 |
| **ORM** | MyBatis + XML Mapper | 灵活 SQL 映射 |
| **认证** | JWT (io.jsonwebtoken) | 无状态令牌 |
| **日志** | Spring AOP + SLF4J | 切面编程 |
| **存储** | 阿里云 OSS SDK | 对象存储 |
| **数据库** | MySQL 8.0 | 关系型数据库 |
| **前端** | Vue 3 (4个页面) | 配合展示 |
| **密码** | BCrypt / MD5 | 密码加密 |

---

## 📁 项目结构

```
tlias-web-management/
│
├── TliasWebManagementApplication.java  # 🚀 启动类
│
├── controller/                          # 🌐 控制器层
│   ├── DeptController.java              # GET/POST/PUT/DELETE /depts
│   ├── EmpController.java               # GET/POST/PUT/DELETE /emps
│   ├── LoginController.java             # POST /login
│   ├── UploadController.java            # POST /upload
│   └── OperateLogController.java        # GET /operateLogs
│
├── service/                             # 💼 业务接口
│   ├── DeptService.java
│   ├── EmpService.java
│   ├── DeptLogService.java
│   ├── EmpLogService.java
│   └── impl/                            # 实现类
│       ├── DeptServiceImpl.java
│       ├── EmpServiceImpl.java
│       ├── DeptLogServiceImpl.java
│       └── EmpLogServiceImpl.java
│
├── mapper/                              # 🗄️ MyBatis接口
│   ├── DeptMapper.java                  # 部门 Mapper
│   ├── EmpMapper.java                   # 员工 Mapper
│   ├── DeptLogMapper.java               # 部门日志 Mapper
│   ├── EmpLogMapper.java                # 员工日志 Mapper
│   ├── OperateLogMapper.java            # 操作日志 Mapper
│   └── OperateLogMapper.xml             # 操作日志 XML
│
├── entity/                              # 📦 实体类
│   ├── Dept.java                        # 部门实体
│   ├── Emp.java                         # 员工实体
│   ├── DeptLog.java                     # 部门日志实体
│   ├── EmpLog.java                      # 员工日志实体
│   └── OperateLog.java                  # 操作日志实体
│
├── filter/                              # 🔍 过滤器
│   ├── LoginCheckFilter.java            # JWT 验证过滤器
│   └── DemoFilterr.java                 # 示例过滤器
│
├── interceptor/                         # 🛡️ 拦截器
│   └── LoginCheckInterceptor.java       # 登录检查拦截器
│
├── aop/                                 # ✂️ 切面
│   └── LogAspect.java                   # 操作日志切面
│   (自动记录所有 @Log 注解标注的方法)
│
├── utils/                               # 🧰 工具类
│   ├── JwtUtils.java                    # JWT 生成/解析
│   ├── AliOSSUtils.java                 # 阿里云 OSS 上传
│   ├── AliOSSProperties.java            # OSS 配置属性
│   └── PasswordUtils.java               # 密码加密
│
├── common/                              # 📋 公共类
│   ├── Result.java                      # 统一响应 {code,msg,data}
│   ├── PageBean.java                    # 分页 {total,rows}
│   └── GlobalExceptionHandler.java      # 全局异常处理
│
├── config/                              # ⚙️ 配置
│   ├── WebConfig.java                   # 拦截器注册
│   └── (SpringBootConfiguration)        # 自动配置
│
├── annotation/                          # 🏷️ 注解
│   └── Log.java                         # @Log 自定义注解
│
└── index(1-4).vue                       # 🎨 Vue 前端页面
```

---

## 📡 API 接口

### 🔐 认证

| 方法 | 路径 | 说明 | JWT |
|------|------|------|-----|
| POST | `/login` | 用户登录 | 返回Token |

**请求:**
```json
{ "username": "admin", "password": "123456" }
```
**响应:**
```json
{
  "code": 200,
  "msg": "success",
  "data": { "token": "eyJhbGciOiJIUzI1NiJ9..." }
}
```

### 🏢 部门管理

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/depts?page=1&pageSize=10&name=研发` | 分页查询 |
| POST | `/depts` | 新增部门 |
| PUT | `/depts/{id}` | 修改部门 |
| DELETE | `/depts/{id}` | 删除部门 |

### 👨‍💼 员工管理

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/emps?page=1&pageSize=10&name=张三&deptId=1&begin=2024-01-01&end=2024-12-31` | 多条件分页查询 |
| POST | `/emps` | 新增员工 |
| PUT | `/emps/{id}` | 修改员工 |
| DELETE | `/emps/{id}` | 删除员工 |

### 📤 文件上传

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/upload` | 上传文件到阿里云OSS |

### 📝 操作日志

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/operateLogs?page=1&pageSize=10` | 分页查询操作日志 |

### 统一响应格式

```json
{
  "code": 200,
  "msg": "success",
  "data": {}
}
```

| code | 说明 |
|------|------|
| 200 | 成功 |
| 400 | 参数错误 |
| 401 | 未授权 |
| 500 | 服务器错误 |

---

## 🔐 安全设计

### JWT 认证流程

```
1. 客户端 POST /login {username, password}
       ↓
2. 服务端验证凭据，生成 JWT Token
       ↓
3. 客户端存储 Token (localStorage)
       ↓
4. 后续请求在 Header 中携带:
   Authorization: Bearer <token>
       ↓
5. LoginCheckFilter 拦截请求，解析 Token
       ↓
6. 验证通过 → 放行到 Controller
   验证失败 → 返回 401
```

### 双层拦截

| 层级 | 组件 | 职责 |
|------|------|------|
| **Filter** | LoginCheckFilter | 最先拦截，检查 Token 是否存在且有效 |
| **Interceptor** | LoginCheckInterceptor | Filter 之后，补充业务校验 |

---

## 📝 日志系统

### AOP 自动记录

```java
@Log  // 自定义注解
@PostMapping
public Result addDept(@RequestBody Dept dept) {
    deptService.add(dept);
    return Result.success();
}
// → LogAspect 自动拦截，记录操作人/时间/方法/参数
```

### 日志实体

| 字段 | 说明 |
|------|------|
| operateUser | 操作人 ID |
| operateTime | 操作时间 |
| className | 类名 |
| methodName | 方法名 |
| methodParams | 方法参数 |
| returnValue | 返回值 |
| costTime | 耗时(ms) |

---

## 🚀 快速开始

### 📋 环境要求

| 软件 | 版本 |
|------|------|
| JDK | 17+ |
| MySQL | 8.0+ |
| Maven | 3.6+ |

### 🔧 启动

```bash
# 1. 创建数据库
mysql -u root -p -e "CREATE DATABASE tlias"

# 2. 修改配置
# 编辑 application.properties/yml
# 设置数据库连接信息

# 3. 启动
mvn spring-boot:run
```

访问 http://localhost:8080

### 🔑 测试账号

| 用户名 | 密码 | 角色 |
|--------|------|------|
| `admin` | `123456` | 管理员 |

---

## 🐛 常见问题

<details>
<summary><b>Q: JWT Token 过期</b></summary>

默认有效期在 JwtUtils 中配置，可根据需要调整过期时间。
</details>

<details>
<summary><b>Q: 阿里云 OSS 上传失败</b></summary>

检查 `AliOSSProperties` 中的 accessKeyId、accessKeySecret、bucket 是否正确配置。
</details>

<details>
<summary><b>Q: 跨域问题</b></summary>

`WebConfig.java` 中已配置 CORS，如需调整允许的域名，修改 `allowedOrigins`。
</details>

---

## ☕ 支持作者

如果这个项目对你有帮助，欢迎请我喝杯咖啡~

<div align="center">

| <img src="https://raw.githubusercontent.com/China-ocean001/campus-repair-platform/master/sponsor/alipay.jpg" width="260" alt="支付宝"> | <img src="https://raw.githubusercontent.com/China-ocean001/campus-repair-platform/master/sponsor/wechat.jpg" width="260" alt="微信支付"> |
|:---:|:---:|
| **支付宝** | **微信支付** |

</div>

---

## 📄 License

MIT License — 仅供学习交流使用

---

<div align="center">

**⭐ 如果这个项目对你有帮助，请给一个 Star！**

</div>
