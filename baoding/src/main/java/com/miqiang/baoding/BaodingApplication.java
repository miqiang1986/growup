package com.miqiang.baoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.miqiang.**.mapper")
public class BaodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaodingApplication.class, args);
    }

}
