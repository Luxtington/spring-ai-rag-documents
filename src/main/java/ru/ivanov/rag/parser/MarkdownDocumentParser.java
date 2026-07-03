package ru.ivanov.rag.parser;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Component
public class MarkdownDocumentParser implements DocumentParser {

    @Override
    public boolean supports(String extension) {
        return extension.equalsIgnoreCase("md")
                || extension.equalsIgnoreCase("markdown");
    }

    @Override
    public String parse(MultipartFile file) throws Exception {
        return new String(file.getBytes(), StandardCharsets.UTF_8);
    }

}