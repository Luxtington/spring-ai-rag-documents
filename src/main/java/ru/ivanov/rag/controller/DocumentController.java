package ru.ivanov.rag.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ivanov.rag.dto.document.UploadDocumentResponse;
import ru.ivanov.rag.entity.DocumentInfo;
import ru.ivanov.rag.service.DocumentService;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/v1/chat/completions
@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<UploadDocumentResponse> upload(
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        return ResponseEntity.ok(documentService.upload(file));
    }

    @GetMapping
    public ResponseEntity<List<DocumentInfo>> getDocuments() {
        return ResponseEntity.ok(documentService.getDocuments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        documentService.delete(id);

        return ResponseEntity.noContent().build();
    }

}