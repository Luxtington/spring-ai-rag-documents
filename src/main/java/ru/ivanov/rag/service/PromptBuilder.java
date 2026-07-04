package ru.ivanov.rag.service;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PromptBuilder {

    public String buildPrompt(String question, List<Document> documents) {

        String context = documents.stream()
                .map(doc -> doc.getText()
                        + "\nИсточник: "
                        + doc.getMetadata().getOrDefault("fileName", "unknown"))
                .collect(Collectors.joining("\n\n--------------------\n\n"));

        return """
                Ты — помощник, отвечающий на основе предоставленного контекста.

                Правила:

                1. Используй только информацию из контекста.
                2. Не придумывай факты.
                3. Если ответа нет — скажи, что информации недостаточно.
                4. По возможности указывай источник.

                Контекст:

                %s

                Вопрос:

                %s
                """.formatted(context, question);
    }

}