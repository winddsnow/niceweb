package com.example.demo.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Web MVC 配置 — 注册自定义 HandlerMapping
 *
 * 通过实现 WebMvcRegistrations 接口，替换默认的 RequestMappingHandlerMapping
 * 为自定义的 PermissionRequestMappingHandlerMapping，实现路由级权限校验。
 *
 * ★ WebMvcRegistrations 自 Spring 4.3 引入，完全兼容 JDK 1.8 / Spring Boot 2.x
 * @author walter
 */
@Configuration
public class WebMvcConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new PermissionRequestMappingHandlerMapping();
    }
}
