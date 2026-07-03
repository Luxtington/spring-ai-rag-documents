package ru.ivanov.rag.dto.chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionRequest {

    @NotNull
    private String model;

    @NotEmpty
    private List<Message> messages;

    private Boolean stream = false;

    private Double temperature;

    private Integer max_tokens;

}