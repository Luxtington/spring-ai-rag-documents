package ru.ivanov.rag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.rag.chunk.TextChunker;
import ru.ivanov.rag.config.DocumentProperties;
import ru.ivanov.rag.dto.document.UploadDocumentResponse;
import ru.ivanov.rag.entity.DocumentInfo;
import ru.ivanov.rag.parser.DocumentParser;
import ru.ivanov.rag.parser.ParserFactory;
import ru.ivanov.rag.repository.DocumentRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final ParserFactory parserFactory;
    private final TextChunker textChunker;
    private final DocumentRepository repository;
    private final EmbeddingService embeddingService;
    private final DocumentProperties documentProperties;

    public UploadDocumentResponse upload(MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (file.getSize() > documentProperties.getMaxFileSize()) {
            throw new IllegalArgumentException("Maximum file size exceeded");
        }

        String fileName = file.getOriginalFilename();

        if (fileName == null) {
            throw new IllegalArgumentException("Filename is empty");
        }

        String extension = getExtension(fileName);

        DocumentParser parser = parserFactory.getParser(extension);

        String text = parser.parse(file);

        List<String> chunks = textChunker.chunk(text);

        UUID documentId = UUID.randomUUID();

        List<String> vectorIds = embeddingService.saveChunks(
                documentId,
                fileName,
                chunks
        );

        DocumentInfo info = DocumentInfo.builder()
                .id(documentId)
                .fileName(fileName)
                .status("COMPLETED")
                .chunkCount(chunks.size())
                .vectorIds(vectorIds)
                .build();

        repository.save(info);

        return UploadDocumentResponse.builder()
                .id(info.getId())
                .fileName(info.getFileName())
                .status(info.getStatus())
                .chunkCount(info.getChunkCount())
                .build();
    }

    public List<DocumentInfo> getDocuments() {
        return repository.findAll();
    }

    public void delete(UUID id) {

        DocumentInfo document = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));

        embeddingService.deleteChunks(document.getVectorIds());

        repository.delete(id);
    }

    private String getExtension(String fileName) {

        int index = fileName.lastIndexOf('.');

        if (index == -1) {
            throw new IllegalArgumentException("Extension not found");
        }

        return fileName.substring(index + 1).toLowerCase();
    }

}