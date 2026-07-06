package ru.ivanov.rag.dto.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ModelsResponse {

    private String object;

    private List<ModelInfo> data;

}