package com.miqiang.baoding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-19  19:13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value(value = "${swagger.enabled}")
    private boolean swaggerEnabled;

    @Value(value = "${swagger.title}")
    private String swaggerTitle;

    @Value(value = "${swagger.description}")
    private String swaggerDescription;

    @Value(value = "${swagger.version}")
    private String swaggerVersion;

    @Value(value = "${swagger.package}")
    private String swaggerPackage;

    @Value(value = "${swagger.contact.name}")
    private String swaggerContactName;

    @Value(value = "${swagger.contact.url}")
    private String swaggerContactUrl;

    @Value(value = "${swagger.contact.email}")
    private String swaggerContactEmail;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                .paths(PathSelectors.any())
                .build();
    }

    //构建api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(swaggerTitle)
                //创建人
                .contact(new Contact(swaggerContactName, swaggerContactUrl, swaggerContactEmail))
                //版本号
                .version(swaggerVersion)
                //描述
                .description(swaggerDescription)
                .build();
    }
}

