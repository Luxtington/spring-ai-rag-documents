package ru.ivanov.rag.service;

import org.springframework.stereotype.Service;
import ru.ivanov.rag.dto.models.ModelInfo;
import ru.ivanov.rag.dto.models.ModelsResponse;

import java.util.List;

@Service
public class ModelService {

    public ModelsResponse getModels() {

        ModelInfo model = new ModelInfo(
                "glm-4.7",
                "model",
                "local"
        );

        return new ModelsResponse(
                "list",
                List.of(model)
        );
    }

}