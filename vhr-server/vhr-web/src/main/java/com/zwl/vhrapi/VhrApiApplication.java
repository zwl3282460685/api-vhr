package com.zwl.vhrapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.zwl.vhrapi.mapper")
@EnableCaching
@EnableScheduling
public class VhrApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(VhrApiApplication.class, args);
    }
}
