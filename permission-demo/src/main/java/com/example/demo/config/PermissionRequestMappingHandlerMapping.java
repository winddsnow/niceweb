package com.example.demo.config;

import com.example.demo.annotation.Permission;
import com.example.demo.condition.PermissionRequestCondition;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 自定义 RequestMappingHandlerMapping
 *
 * 重写 getCustomMethodCondition 方法，在路由匹配阶段注入权限条件。
 * 这样 Spring MVC 在分发请求时会调用 PermissionRequestCondition.getMatchingCondition()
 * 完成权限校验 —— 完全跳过 AOP 切面。
 * @author walter
 */
public class PermissionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        Permission annotation = method.getAnnotation(Permission.class);
        if (annotation == null) {
            return null;
        }

        // ignore=true 表示公开接口，跳过权限校验
        if (annotation.ignore()) {
            return null;
        }

        return new PermissionRequestCondition(annotation, getApplicationContext());
    }
}
