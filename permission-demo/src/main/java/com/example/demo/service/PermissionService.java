package com.example.demo.service;

import com.example.demo.constant.PermissionConstants;
import org.springframework.stereotype.Service;

/**
 * 自定义权限校验 Bean，用于 SpEL 表达式引用
 *
 * 在 @Permission 注解中可通过 expression 属性引用此 Bean：
 * {@code @Permission(expression = "#{@ps.hasPerm('sys:user:save')}")}
 * @author walter
 */
@Service("ps")
public class PermissionService {

    /**
     * 判断当前用户是否拥有指定权限
     *
     * @param permission 权限码
     * @return true 有权限 / false 无权限
     */
    public boolean hasPerm(String permission) {
        // 模拟：只有 SYS_USER_ADD 权限返回 true
        return PermissionConstants.SYS_USER_ADD.equals(permission);
    }
}
