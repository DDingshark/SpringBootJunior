package com.springboot.api.config;
// REST API 명세를 문서화 하는 방법
/*
    API 를 개발하면 명세를 잘 관리 해야한다.
            > 명세 : 해당 API 가 어떤 로직을 수행하는지,
                어떤 값을 가져야 하는지
                이에 따른 응답은 무엇인지 잘 정리한 자료

    Swagger을 사용하면 알아서 잘 정리해주니까 사용하도록 해봅시당.
 */



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
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Open API Test with Swagger")
                .description("띵주의 API Test with Swagger.")
                .version("1.0.0")
                .build();

    }
}
