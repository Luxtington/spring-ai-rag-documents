package ru.ivanov.rag.dto.document;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UploadDocumentResponse {

    private UUID id;

    private String fileName;

    private String status;

    private int chunkCount;

}