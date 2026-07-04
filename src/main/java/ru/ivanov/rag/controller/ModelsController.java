package ru.ivanov.rag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ivanov.rag.dto.models.ModelsResponse;
import ru.ivanov.rag.service.ModelService;

@RestController
@RequestMapping("/v1/models")
@RequiredArgsConstructor
public class ModelsController {

    private final ModelService modelService;

    @GetMapping
    public ModelsResponse getModels() {
        return modelService.getModels();
    }

}