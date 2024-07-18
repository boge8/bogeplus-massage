package com.bogeplus.massage.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bogeplus"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.bogeplus.massage.user.mapper"})
@EnableFeignClients(basePackages = {"com.bogeplus.message.feign"})
public class BogeplusUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusUserServiceApplication.class, args);
    }

}
