package ru.ivanov.rag.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatMessageResponse {

    private String role;

    private String content;

}