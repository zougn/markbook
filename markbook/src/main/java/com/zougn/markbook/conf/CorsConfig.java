package com.zougn.markbook.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 生产环境建议指定具体域名
                .allowedOrigins("*")
                // 必须包含OPTIONS方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization") // 允许前端读取的Header
                .allowCredentials(false) // 非必要不开启
                .maxAge(3600); // 预检请求缓存时间
    }
}

