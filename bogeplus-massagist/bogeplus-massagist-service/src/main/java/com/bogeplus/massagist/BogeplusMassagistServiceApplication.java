package com.bogeplus.massagist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 描述: [技师模块-启动类]
 * 作者: 竹子
 */

@SpringBootApplication
@MapperScan("com.bogeplus.massagist.mapper")
public class BogeplusMassagistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BogeplusMassagistServiceApplication.class,args);
    }
}
