package ru.ivanov.rag.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentInfo {

    @Id
    private UUID id;

    private String fileName;

    private String status;

    private int chunkCount;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "document_vectors",
            joinColumns = @JoinColumn(name = "document_id")
    )
    @Column(name = "vector_id")
    @Builder.Default
    private List<String> vectorIds = new ArrayList<>();
}