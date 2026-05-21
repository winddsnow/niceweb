package com.example.demo.controller;

import com.example.demo.annotation.Permission;
import com.example.demo.constant.PermissionConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试 Controller
 *
 * 注意：当权限不满足时，因为是路由层面的拒绝，
 * Spring MVC 会返回 404（路由不匹配），而非 403。
 * 生产环境中可通过全局异常处理器或自定义 HandlerMapping
 * 改写为 403 响应。
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/list")
    @Permission(value = {PermissionConstants.SYS_USER_LIST},
                module = "用户管理",
                description = "查询用户列表")
    public String list() {
        return "✅ 用户列表查询接口 — 有权限";
    }

    @GetMapping("/create")
    @Permission(module = "用户管理",
                description = "创建用户",
                expression = "#{@ps.hasPerm('sys:user:save')}")
    public String create() {
        return "✅ 创建用户接口 — SpEL 表达式校验通过";
    }

    @GetMapping("/delete")
    @Permission(value = {PermissionConstants.SYS_USER_DELETE},
                module = "用户管理",
                description = "删除用户（当前用户无此权限，会返回 404）")
    public String delete() {
        return "✅ 删除用户接口";
    }

    @GetMapping("/public")
    @Permission(ignore = true, description = "公开接口，无需权限")
    public String publicApi() {
        return "✅ 公开接口 — 所有人都可访问";
    }

    @GetMapping("/roles")
    @Permission(value = {PermissionConstants.SYS_ROLE_LIST},
                module = "角色管理",
                description = "查询角色列表")
    public String roles() {
        return "✅ 角色列表查询接口 — 有权限";
    }

    @GetMapping("/logs")
    @Permission(value = {PermissionConstants.SYS_LOG_QUERY},
                module = "日志管理",
                description = "查询日志")
    public String logs() {
        return "✅ 日志查询接口 — 有权限";
    }

    @GetMapping("/multi-perm")
    @Permission(value = {
            PermissionConstants.SYS_USER_LIST,
            PermissionConstants.SYS_USER_ADD
        }, module = "用户管理",
           description = "多权限测试（拥有任意一个即可访问）")
    public String multiPerm() {
        return "✅ 多权限接口 — 当前用户拥有 SYS_USER_LIST，可访问";
    }
}
