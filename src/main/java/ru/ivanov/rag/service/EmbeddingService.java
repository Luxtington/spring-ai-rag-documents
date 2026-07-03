package ru.ivanov.rag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final VectorStore vectorStore;

    public List<String> saveChunks(UUID documentId,
                                   String fileName,
                                   List<String> chunks) {

        List<Document> documents = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        for (int i = 0; i < chunks.size(); i++) {

            String chunkId = UUID.randomUUID().toString();

            ids.add(chunkId);

            documents.add(
                    new Document(
                            chunkId,
                            chunks.get(i),
                            Map.of(
                                    "documentId", documentId.toString(),
                                    "fileName", fileName,
                                    "chunkIndex", i
                            )
                    )
            );
        }

        vectorStore.add(documents);

        return ids;
    }

    public void deleteChunks(List<String> ids) {

        if (ids == null || ids.isEmpty()) {
            return;
        }

        vectorStore.delete(ids);
    }

}