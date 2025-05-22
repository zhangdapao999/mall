package com.lin.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 对所有路径应用CORS配置
                .allowedOrigins("*") //
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的方法
                .allowedHeaders("*") // 允许的头部信息，可以使用"*"但不建议在生产环境中使用
                .allowCredentials(true) // 是否发送Cookie等凭证信息
                .maxAge(3600); // 预检请求的缓存时间（秒）
    }
}