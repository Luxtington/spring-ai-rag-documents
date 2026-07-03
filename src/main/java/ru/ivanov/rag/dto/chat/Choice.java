package ru.ivanov.rag.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Choice {

    private Integer index;

    private ChatMessageResponse message;

    private String finish_reason;

}