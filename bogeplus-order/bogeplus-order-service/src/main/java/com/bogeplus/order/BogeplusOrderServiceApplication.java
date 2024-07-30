package com.bogeplus.order;

import com.bogeplus.order.client.MassagistFeigenClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackageClasses = MassagistFeigenClient.class)
@MapperScan("com.bogeplus.order.mapper")
@ComponentScan(basePackages = {"com.bogeplus"})
@EnableTransactionManagement
public class BogeplusOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusOrderServiceApplication.class, args);
    }

}
