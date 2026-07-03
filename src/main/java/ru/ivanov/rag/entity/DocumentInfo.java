package ru.ivanov.rag.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentInfo {

    private UUID id;

    private String fileName;

    private String status;

    private int chunkCount;

    /**
     * id всех документов (чанков) в Qdrant
     */
    private List<String> vectorIds;
}