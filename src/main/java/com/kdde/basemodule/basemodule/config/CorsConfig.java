package com.kdde.basemodule.basemodule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zxx
 * @Date 2025/5/16 14:56
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 添加CROS支持以实现和前端联调
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有路径
                .allowedOrigins("http://192.168.27.103:8080", "http://192.168.27.103:8081", "http://127.0.0.1:8080") // 此处配置前端的请求ip + 端口
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                .allowedHeaders("*") // 允许的请求头
                .exposedHeaders("Authorization", "Content-Disposition") // 暴露的响应头
                .allowCredentials(true) // 允许凭证
                .maxAge(3600); // 预检请求缓存时间(秒)
    }
}
