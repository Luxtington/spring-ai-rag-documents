package ru.ivanov.rag.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PdfParser implements DocumentParser {

    @Override
    public boolean supports(String extension) {
        return "pdf".equalsIgnoreCase(extension);
    }

    @Override
    public String parse(MultipartFile file) throws Exception {

        PagePdfDocumentReader reader =
                new PagePdfDocumentReader(
                        new ByteArrayResource(file.getBytes())
                );

        List<Document> pages = reader.get();

        return pages.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));
    }
}