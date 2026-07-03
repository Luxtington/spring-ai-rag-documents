package ru.ivanov.rag.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rag.documents")
public class DocumentProperties {

    private long maxFileSize;

}
