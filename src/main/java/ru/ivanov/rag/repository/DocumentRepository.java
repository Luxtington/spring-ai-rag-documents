package ru.ivanov.rag.repository;

import org.springframework.stereotype.Repository;
import ru.ivanov.rag.entity.DocumentInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DocumentRepository {

    private final Map<UUID, DocumentInfo> storage = new ConcurrentHashMap<>();

    public void save(DocumentInfo document) {
        storage.put(document.getId(), document);
    }

    public Optional<DocumentInfo> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<DocumentInfo> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void delete(UUID id) {
        storage.remove(id);
    }

}