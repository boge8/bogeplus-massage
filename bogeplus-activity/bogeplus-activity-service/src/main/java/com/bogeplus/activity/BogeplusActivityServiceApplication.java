package com.bogeplus.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bogeplus.activity.mapper")
@ComponentScan(basePackages = {"com.bogeplus"})
public class BogeplusActivityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusActivityServiceApplication.class, args);
    }

}
