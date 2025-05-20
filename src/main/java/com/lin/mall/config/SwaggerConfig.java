package com.lin.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("API文档")
                        .description("这是一个API文档")
                        .version("1.0")
                        .build())
                .select()
                // 指定要生成API文档的包路径，这里以所有controller为例
                .apis(RequestHandlerSelectors.basePackage("com.lin.mall.controller"))
                // 指定要生成API文档的路径，这里以所有路径为例
                .paths(PathSelectors.any())
                .build();
    }
}