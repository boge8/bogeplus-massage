package com.bogeplus.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 描述: [技师模块-启动类]
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.bogeplus"})
@MapperScan("com.bogeplus.order.mapper")
@EnableTransactionManagement
public class BogeplusMassagistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusMassagistServiceApplication.class,args);
    }
}