package ru.ivanov.rag.parser;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentParser {

    boolean supports(String extension);

    String parse(MultipartFile file) throws Exception;

}