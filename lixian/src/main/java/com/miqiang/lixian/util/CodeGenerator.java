package com.miqiang.lixian.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    public static void main(String[] args) {
        System.out.println("请输入作者名称：");
        String author = new Scanner(System.in).next();
        System.out.println("请输入模块名称：");
        String moduleName = new Scanner(System.in).next();

        FastAutoGenerator.create("jdbc:mysql://192.168.137.200:3339/test?serverTimezone=UTC", "root", "123456")
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(author)
                        .enableSwagger().fileOverride()
                        .outputDir("E://IdeaProjects/growup/"+moduleName+"/src/main/java"))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.miqiang")
                    .moduleName(moduleName))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .addTablePrefix("tab_", "sys_")
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok()
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT)
                        ).build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                 */
               .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
