package ru.ivanov.rag.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.rag.dto.chat.ChatCompletionRequest;
import ru.ivanov.rag.dto.chat.ChatCompletionResponse;
import ru.ivanov.rag.service.ChatService;

@RestController
@RequestMapping("/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/completions")
    public ChatCompletionResponse chat(
            @RequestBody @Valid ChatCompletionRequest request
    ) {
        return chatService.chat(request);
    }

}