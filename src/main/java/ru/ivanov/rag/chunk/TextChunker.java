package ru.ivanov.rag.chunk;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ivanov.rag.config.ChunkProperties;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TextChunker {

    private final ChunkProperties properties;

    public List<String> chunk(String text) {

        List<String> chunks = new ArrayList<>();

        int chunkSize = properties.getSize();
        int overlap = properties.getOverlap();

        int start = 0;

        while (start < text.length()) {

            int end = Math.min(start + chunkSize, text.length());

            chunks.add(text.substring(start, end));

            if (end == text.length()) {
                break;
            }

            start = end - overlap;
        }

        return chunks;
    }

}