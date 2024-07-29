package com.bogeplus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class Main {
        public static void main(String[] args) {
            String url="jdbc:mysql://119.3.230.36:3306/massage_activity?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
            String username="rw_user";
            String password="Qq123123";

            FastAutoGenerator.create( url,username,password)
                    .globalConfig(builder -> {
                        builder.author("bogeplus") // 设置作者
                                .enableSwagger() // 开启 swagger 模式
                                .outputDir("E:\\bgPlus\\bogeplus-massage\\bogeplus-activity\\bogeplus-activity-service\\src\\main\\java"); // 指定输出目录
                    })
                    .dataSourceConfig(builder ->
                            builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                                int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                                if (typeCode == Types.SMALLINT) {
                                    // 自定义类型转换
                                    return DbColumnType.INTEGER;
                                }
                                return typeRegistry.getColumnType(metaInfo);
                            })
                    )
                    .packageConfig(builder ->
                            builder.parent("com.bogeplus") // 设置父包名
                                    .moduleName("activity") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\bgPlus\\bogeplus-massage\\bogeplus-activity\\bogeplus-activity-service\\src\\main\\resources\\mapper")) // 设置mapperXml生成路径
                    )
                    .strategyConfig(builder ->
                            builder
                                    .addTablePrefix("massage_") // 设置过滤表前缀
                                    .entityBuilder()
                                    .enableLombok()
                                    .controllerBuilder()
                                    .enableRestStyle() // 启用 REST 风格
                    )
                    .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                    .execute();

    }
}