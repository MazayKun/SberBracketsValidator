package ru.mikheev.kirill.bracketsvalidator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mikheev.kirill.bracketsvalidator.exception.TextProcessingException;
import ru.mikheev.kirill.bracketsvalidator.service.contract.BracketsValidationOperations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Service
public class BracketsValidationService implements BracketsValidationOperations {

    @Override
    public boolean validateBracketsSequenceWithText(String content) {
        try(InputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
            return validateBracketsSequenceWithText(inputStream);
        } catch (IOException e) {
            throw new TextProcessingException(e);
        }
    }

    private boolean validateBracketsSequenceWithText(InputStream content) throws IOException {
        Queue<Character> queue = new LinkedList<>();
        boolean hasContentBetween = false;
        while(content.available() > 0) {
            int curr = content.read();
            switch (curr) {
                case '{', '[', '(' -> {
                    hasContentBetween = false;
                    queue.add((char) curr);
                }
                case '}' -> {
                    if (!hasContentBetween || queue.isEmpty() || queue.poll() != '{') return false;
                    hasContentBetween = false;
                }
                case ']' -> {
                    if (!hasContentBetween || queue.isEmpty() || queue.poll() != '[') return false;
                    hasContentBetween = false;
                }
                case ')' -> {
                    if (!hasContentBetween || queue.isEmpty() || queue.poll() != '(') return false;
                    hasContentBetween = false;
                }
                default -> hasContentBetween = true;
            }
        }
        return queue.isEmpty();
    }
}
