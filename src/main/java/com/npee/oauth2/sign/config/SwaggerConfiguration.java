package com.npee.oauth2.sign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.npee.oauth2.sign.controller"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .useDefaultResponseMessages(false);
    }

    public ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation")
                .description("Server API Docs")
                .license("Npee")
                .licenseUrl("https://github.com/npee/myapi")
                .version("1")
                .build();
    }
}
