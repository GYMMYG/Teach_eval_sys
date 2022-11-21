package com.example.se;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan("com.example.se.mapper")
public class SeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeApplication.class, args);
        System.out.println("shengchenghaole");
    }

}
