package ru.ivanov.rag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.rag.entity.DocumentInfo;

import java.util.UUID;

@Repository
public interface DocumentRepository
        extends JpaRepository<DocumentInfo, UUID> {
}