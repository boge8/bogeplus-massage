package com.bogeplus.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.bogeplus"})
@EnableDiscoveryClient
public class BogeplusUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusUserServiceApplication.class, args);
    }

}
