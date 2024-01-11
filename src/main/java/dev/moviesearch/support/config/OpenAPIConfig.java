package dev.moviesearch.support.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("영화 검색 API")
                .version("1.0")
                .description("Spring Boot를 이용한 영화 검색 웹 애플리케이션 API 입니다.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact()
                        .name("컨택")
                        .url("웹주소")
                        .email("이메일"))
                .license(new License()
                        .name("Apache License Version 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0")
                );

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

