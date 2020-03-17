package com.chuyue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chuyue.mapper")
public class BdcscTeskinterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BdcscTeskinterfaceApplication.class, args);
    }

}
