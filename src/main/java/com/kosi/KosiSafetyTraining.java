package com.kosi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
@EnableCaching
@ComponentScan("com.kosi")
public class KosiSafetyTraining extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(KosiSafetyTraining.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(KosiSafetyTraining.class);
    }



}