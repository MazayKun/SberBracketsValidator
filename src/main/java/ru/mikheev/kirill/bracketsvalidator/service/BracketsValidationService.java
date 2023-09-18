package ru.mikheev.kirill.bracketsvalidator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mikheev.kirill.bracketsvalidator.exception.TextProcessingException;
import ru.mikheev.kirill.bracketsvalidator.service.contract.BracketsValidationOperations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

@Slf4j
@Service
public class BracketsValidationService implements BracketsValidationOperations {

    @Override
    public boolean validateBracketsSequenceWithText(String textForValidation) {
        try(InputStream textStream = new ByteArrayInputStream(textForValidation.getBytes(StandardCharsets.UTF_8))) {
            return validateBracketsSequenceWithText(textStream);
        } catch (IOException e) {
            throw new TextProcessingException(e);
        }
    }

    private boolean validateBracketsSequenceWithText(InputStream textForValidationStream) throws IOException {
        Deque<Character> queue = new LinkedList<>();
        boolean hasContentBetween = false;
        while(textForValidationStream.available() > 0) {
            int currChar = textForValidationStream.read();
            switch (currChar) {
                case '{', '[', '(' -> {
                    hasContentBetween = false;
                    queue.add((char) currChar);
                }
                case '}' -> {
                    if (!hasContentBetween || queue.isEmpty() || queue.pollLast() != '{') return false;
                }
                case ']' -> {
                    if (!hasContentBetween || queue.isEmpty() || queue.pollLast() != '[') return false;
                }
                case ')' -> {
                    if (!hasContentBetween || queue.isEmpty() || queue.pollLast() != '(') return false;
                }
                default -> hasContentBetween = true;
            }
        }
        return queue.isEmpty();
    }
}
