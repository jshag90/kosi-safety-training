package com.kosi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/static/image/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/script/**").addResourceLocations("classpath:/static/script/").setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").setCachePeriod(60 * 60 * 24 * 365);
    }

}
