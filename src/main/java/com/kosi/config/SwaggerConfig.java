package com.kosi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kosi")) // 패키지 경로 수정
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(apiKey()))  // 🔹 Bearer Token 인증 추가
                .securityContexts(Collections.singletonList(securityContext())); // 🔹 보안 컨텍스트 추가

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("KOSI 온라인 수강 시스템 API")  // 문서 제목
                .description("KOSI 온라인 수강 시스템 API 문서입니다.") // 문서 설명
                .version("1.0.0")  // 문서 버전
                .license("Apache License 2.0") // 라이선스 정보
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    // 🔹 Bearer Token 인증 추가 (Bearer 키워드 자동 포함)
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    // 🔹 보안 컨텍스트 설정
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    // 🔹 인증 범위 설정 (Bearer 자동 추가)
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }
}
