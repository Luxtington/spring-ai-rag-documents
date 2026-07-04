package ru.ivanov.rag.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ragApi() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("RAG Service")
                                .version("1.0")
                                .description("OpenAI compatible RAG service")
                );
    }

}