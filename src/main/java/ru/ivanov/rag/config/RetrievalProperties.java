package ru.ivanov.rag.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rag.retrieval")
public class RetrievalProperties {

    private int topK;

    private double similarityThreshold;

}