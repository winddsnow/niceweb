package com.example.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义权限注解
 * 用于 Controller 接口的权限控制，支持 SpEL 表达式
 *
 * 使用示例：
 * {@code
 * @GetMapping("/list")
 * @Permission(value = {"sys:user:list"}, module = "用户管理", description = "查询用户列表")
 * public String list() { return "ok"; }
 * }
 * @author walter
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /**
     * 权限码（必填）
     * 如：sys:user:list
     */
    String[] value() default {};

    /**
     * 忽略权限校验（公开接口、登录即可访问、超管放行）
     */
    boolean ignore() default false;

    /**
     * 权限描述（用于生成接口文档、权限管理后台展示）
     */
    String description() default "";

    /**
     * 模块名称（用于接口归类：用户管理、订单管理、系统配置）
     */
    String module() default "";

    /**
     * 权限表达式；优先级最高，支持 SpEL 表达式
     * 如：#{(ps.hasPerm('sys:user:save'))}
     */
    String expression() default "";
}
