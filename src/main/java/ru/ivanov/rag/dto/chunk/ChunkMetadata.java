package ru.ivanov.rag.dto.chunk;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ChunkMetadata {

    private UUID documentId;

    private String fileName;

    private Integer chunkIndex;

}