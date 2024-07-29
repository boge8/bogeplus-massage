package com.bogeplus.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.bogeplus"})
public class BogeplusMessageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusMessageServiceApplication.class, args);
    }

}
