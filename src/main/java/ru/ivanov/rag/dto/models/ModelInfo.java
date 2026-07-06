package ru.ivanov.rag.dto.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModelInfo {

    private String id;

    private String object;

    private String owned_by;

}