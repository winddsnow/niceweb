package com.example.demo.context;

import com.example.demo.constant.PermissionConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限上下文工具类
 *
 * ★ 重要提示（JDK 1.8 兼容改动）：
 * 原文章使用了 Java 9+ 的 Set.of()，本 Demo 改为 Java 8 兼容实现。
 *
 * 模拟当前用户权限集，实际生产环境应从 SecurityContext / Token 中提取。
 */
public final class PermissionContext {

    private PermissionContext() {
    }

    /**
     * 获取当前用户拥有的权限集
     * 生产环境中从数据库/缓存/Token 动态加载
     */
    public static Set<String> getCurrentUserPermissions() {
        // ★ JDK 1.8 兼容：使用 Collections.unmodifiableSet + new HashSet<>(Arrays.asList(...))
        // 替代 Java 9+ 的 Set.of(...)
        Set<String> permissions = new HashSet<>(Arrays.asList(
                PermissionConstants.SYS_USER_LIST,
                PermissionConstants.SYS_USER_ADD,
                PermissionConstants.SYS_ROLE_LIST,
                PermissionConstants.SYS_MENU_LIST,
                PermissionConstants.SYS_LOG_QUERY
                // 注意：没有 SYS_USER_DELETE，可以用来测试无权限场景
        ));
        return Collections.unmodifiableSet(permissions);
    }

    /**
     * 判断当前用户是否拥有指定权限集合中的任意一个
     */
    public static boolean hasPermission(Set<String> requiredPermissions) {
        if (requiredPermissions == null || requiredPermissions.isEmpty()) {
            return true;
        }
        Set<String> userPermissions = getCurrentUserPermissions();
        for (String perm : requiredPermissions) {
            if (userPermissions.contains(perm)) {
                return true;
            }
        }
        return false;
    }
}
