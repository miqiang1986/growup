package com.miqiang.baoding.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Swagger配置
 */
@EnableSwagger2
@Configuration
//@Profile({"dev","test","poc","future", "local"})
public class SwaggerConfig {

    /**
     * 创建api实例
     * @return
     */
    @Bean
    public Docket createRestAoi(){
        return new Docket(DocumentationType.SWAGGER_2)
                //用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                //设置哪些接口暴露给Swagger展示
                .select()
                //过滤的接口
                .apis(RequestHandlerSelectors.basePackage("com.miqiang.**.controller"))
                //扫描所有有注解的api，用这种方式更灵活，指定为ApiOperation.class
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                //构建
                .build();
    }


    /**
     * 添加摘要信息
     * @return
     */
    private ApiInfo apiInfo(){

        //用ApiInfoBuilder进行定制，可以设置不同的属性，比较方便
        return new ApiInfoBuilder()
                //设置标题
                .title("标题：springboot集成swagger测试")
                //描述
                .description("描述：用于测试集成swagger接口")
                //作者信息
                .contact(new Contact("miqiang",null,null))
                //版本
                .version("版本号：1.0")
                //构建
                .build();


    }


}
