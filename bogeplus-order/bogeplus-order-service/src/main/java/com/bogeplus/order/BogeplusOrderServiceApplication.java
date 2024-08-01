package com.bogeplus.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.bogeplus"})
@MapperScan("com.bogeplus.order.mapper")
@EnableFeignClients(basePackages = {"com.bogeplus.massagist.feign","com.bogeplus.user.feign"})
public class BogeplusOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusOrderServiceApplication.class, args);
    }

}
