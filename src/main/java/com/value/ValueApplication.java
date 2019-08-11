package com.value;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.value.dao")
public class ValueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValueApplication.class, args);
    }

}
