# HandlerMapping 权限控制 Demo — JDK 1.8 兼容版

## 项目说明

基于 Spring MVC 的 HandlerMapping 机制，在**路由匹配阶段**直接完成权限校验，完全绕过 AOP 切面，实现零性能损耗的精细化接口权限管控。

## 技术栈

| 组件 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7.18 | 最新 2.x 稳定版，兼容 JDK 1.8 |
| JDK | 1.8 (Java 8) | 已验证无 Java 9+ API 依赖 |
| Maven | 3.6+ | 构建工具 |

## 架构设计

```
请求到达
    │
    ▼
DispatcherServlet  →  PermissionRequestMappingHandlerMapping
    │                          │
    │                    getCustomMethodCondition()
    │                          │
    │                    PermissionRequestCondition
    │                          │
    │                    getMatchingCondition()
    │                     ┌────┴────┐
    │                  SpEL 表达式  权限码校验
    │                     └────┬────┘
    │                     匹配? true
    │                          │
    ▼                          ▼
   Controller             返回 null → 404（路由不匹配）
```

## 核心组件

| 文件 | 作用 |
|------|------|
| `@Permission` 注解 | 定义权限码、SpEL 表达式、模块信息 |
| `PermissionRequestMappingHandlerMapping` | 继承 `RequestMappingHandlerMapping`，覆写 `getCustomMethodCondition()` |
| `PermissionRequestCondition` | 实现 `RequestCondition`，在 `getMatchingCondition()` 中做权限校验 |
| `WebMvcConfig` | 实现 `WebMvcRegistrations`，注册自定义 HandlerMapping |

## JDK 1.8 兼容说明

原始文章使用 Java 9+ 的 `Set.of()` 和 Spring Boot 3.x（JDK 17+）。
本 Demo 做了以下适配：

- `Set.of(...)` → `Collections.unmodifiableSet(new HashSet<>(Arrays.asList(...)))`
- Spring Boot 3.x → **Spring Boot 2.7.18**（最后支持 JDK 1.8 的大版本）
- 所有 Lambda / Stream 用法均保持在 Java 8 语法范围内

## 接口测试

| 路径 | 权限 | 预期结果 |
|------|------|---------|
| `GET /api/public` | `@Permission(ignore=true)` | ✅ 200 — 公开接口 |
| `GET /api/list` | `sys:user:list` | ✅ 200 — 有权限 |
| `GET /api/roles` | `sys:role:list` | ✅ 200 — 有权限 |
| `GET /api/logs` | `sys:log:query` | ✅ 200 — 有权限 |
| `GET /api/create` | SpEL: `@ps.hasPerm('sys:user:save')` | ✅ 200 — 表达式通过 |
| `GET /api/multi-perm` | `sys:user:list` OR `sys:user:add` | ✅ 200 — 拥有其一 |
| `GET /api/delete` | `sys:user:remove` | ❌ 404 — 无此权限 |

> ⚠️ 注意：由于权限校验在路由匹配阶段完成，不满足条件时 Spring MVC 返回 **404（路由不匹配）** 而非 403。生产环境可通过全局异常处理或自定义 HandlerMapping 包装为 403 响应。

## 启动方式

```bash
cd permission-demo
mvn spring-boot:run
# 或
mvn clean package -DskipTests
java -jar target/permission-demo-1.0.0.jar
```
