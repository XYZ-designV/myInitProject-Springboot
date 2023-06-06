package com.xyz;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.xyz.domain")
public class CommonApp {
    public static void main(String[] args) {
        SpringApplication.run(CommonApp.class, args);
    }
}
