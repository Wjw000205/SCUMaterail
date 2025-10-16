package com.kdde.basemodule.basemodule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yulongtian
 * @create 2024-12-04 23:08
 */
@SpringBootApplication(scanBasePackages = "com.kdde.basemodule.basemodule")
@MapperScan("com.kdde.basemodule.basemodule.dao")
public class MaterialPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaterialPlatformApplication.class, args);
    }
}