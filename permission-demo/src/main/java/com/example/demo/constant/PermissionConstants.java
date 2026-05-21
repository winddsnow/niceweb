package com.example.demo.constant;

/**
 * 权限常量定义
 * 集中管理所有权限码
 * @author walter
 */
public final class PermissionConstants {

    private PermissionConstants() {
        // 工具类，禁止实例化
    }

    // ====== 用户模块 ======
    public static final String SYS_USER_LIST     = "sys:user:list";
    public static final String SYS_USER_ADD      = "sys:user:save";
    public static final String SYS_USER_EDIT     = "sys:user:update";
    public static final String SYS_USER_DELETE   = "sys:user:remove";
    public static final String SYS_USER_RESET_PWD = "sys:user:resetPwd";

    // ====== 角色模块 ======
    public static final String SYS_ROLE_LIST     = "sys:role:list";
    public static final String SYS_ROLE_ADD      = "sys:role:save";
    public static final String SYS_ROLE_EDIT     = "sys:role:update";
    public static final String SYS_ROLE_DELETE   = "sys:role:remove";

    // ====== 菜单模块 ======
    public static final String SYS_MENU_LIST     = "sys:menu:list";

    // ====== 日志模块 ======
    public static final String SYS_LOG_QUERY     = "sys:log:query";
}
