package com.example.demo.condition;

import com.example.demo.annotation.Permission;
import com.example.demo.context.PermissionContext;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义请求条件 — 在路由匹配阶段执行权限校验
 *
 * 核心逻辑：
 * 1. 如果配置了 SpEL 表达式，优先解析表达式决定是否放行
 * 2. 否则基于权限码（value 属性）进行校验
 * 3. 条件不满足时返回 null，Spring MVC 判定 404（路由不匹配）
 * @author walter
 */
public class PermissionRequestCondition implements RequestCondition<PermissionRequestCondition> {

    private static final StandardBeanExpressionResolver expressionResolver =
            new StandardBeanExpressionResolver();

    private final Permission permission;
    private final ApplicationContext context;

    public PermissionRequestCondition(Permission permission, ApplicationContext context) {
        this.permission = permission;
        this.context = context;
    }

    /**
     * 合并条件 — 多个相同条件的接口合并时用，这里直接返回 this
     */
    @Override
    public PermissionRequestCondition combine(PermissionRequestCondition other) {
        return this;
    }

    /**
     * 核心：匹配条件
     * 返回 this 表示匹配（有权限），返回 null 表示不匹配（无权限）
     */
    @Override
    public PermissionRequestCondition getMatchingCondition(HttpServletRequest request) {
        // 1. 优先处理 SpEL 表达式
        String expression = permission.expression();
        if (StringUtils.hasText(expression)) {
            ConfigurableBeanFactory beanFactory = (ConfigurableBeanFactory) context.getAutowireCapableBeanFactory();

            try {
                Object value = expressionResolver.evaluate(
                        expression,
                        new BeanExpressionContext(beanFactory, null)
                );
                Boolean result = beanFactory.getTypeConverter().convertIfNecessary(value, Boolean.class);
                if (Boolean.TRUE.equals(result)) {
                    return this;
                }
                return null;
            } catch (Exception e) {
                // 表达式解析失败，拒绝访问
                return null;
            }
        }

        // 2. 无表达式时，按权限码校验
        String[] permissions = permission.value();
        if (permissions.length == 0) {
            // 没有指定权限码也没有表达式，默认放行
            return this;
        }

        // ★ JDK 1.8 兼容：Arrays.asList 替代 Java 9+ 的 Set.of
        Set<String> required = new HashSet<>(Arrays.asList(permissions));
        if (PermissionContext.hasPermission(required)) {
            return this;
        }

        return null;
    }

    /**
     * 比较优先级
     */
    @Override
    public int compareTo(PermissionRequestCondition other, HttpServletRequest request) {
        return 0;
    }

    public Permission getPermission() {
        return permission;
    }
}
