package com.bogeplus.massagist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述: [技师模块-启动类]
 * 作者: 竹子
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.bogeplus"})
@MapperScan("com.bogeplus.massagist.mapper")
public class BogeplusMassagistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusMassagistServiceApplication.class,args);
    }
}
