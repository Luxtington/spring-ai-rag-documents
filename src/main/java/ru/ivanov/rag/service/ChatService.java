package ru.ivanov.rag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;
import ru.ivanov.rag.dto.chat.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient.Builder chatClientBuilder;
    private final RetrievalService retrievalService;
    private final PromptBuilder promptBuilder;

    public ChatCompletionResponse chat(ChatCompletionRequest request) {

        String question = request.getMessages()
                .get(request.getMessages().size() - 1)
                .getContent();

        List<Document> documents = retrievalService.search(question);

        String prompt = promptBuilder.buildPrompt(question, documents);

        String answer = chatClientBuilder.build()
                .prompt(prompt)
                .call()
                .content();

        return ChatCompletionResponse.builder()
                .id("chatcmpl-" + UUID.randomUUID())
                .object("chat.completion")
                .created(Instant.now().getEpochSecond())
                .model(request.getModel())
                .choices(List.of(
                        new Choice(
                                0,
                                new ChatMessageResponse(
                                        "assistant",
                                        answer
                                ),
                                "stop"
                        )
                ))
                .usage(new Usage(0, 0, 0))
                .build();
    }

}