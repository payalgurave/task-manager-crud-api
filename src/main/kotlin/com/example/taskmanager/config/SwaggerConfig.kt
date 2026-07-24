package com.example.taskmanager.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Task Manager CRUD API")
                    .version("1.0")
                    .description("A CRUD REST API built using Spring Boot, Kotlin, JPA and H2 Database.")
                    .contact(
                        Contact()
                            .name("Payal Gurave")
                            .email("payalgurave30@gmail.com")
                            .url("https://github.com/payalgurave")
                    )
            )
    }
}