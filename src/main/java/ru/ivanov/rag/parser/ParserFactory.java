package ru.ivanov.rag.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParserFactory {

    private final List<DocumentParser> parsers;

    public DocumentParser getParser(String extension) {

        return parsers.stream()
                .filter(parser -> parser.supports(extension))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Unsupported file type: " + extension
                        ));
    }

}