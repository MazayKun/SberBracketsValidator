package ru.mikheev.kirill.bracketsvalidator.service;

import org.springframework.stereotype.Service;
import ru.mikheev.kirill.bracketsvalidator.service.contract.BracketsValidationOperations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class BracketsValidationService implements BracketsValidationOperations {
    @Override
    public boolean validateBracketsSequenceWithText(String content) {
        Queue<Character> queue = new LinkedList<>();
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
            boolean hasContentBetween = false;
            while(byteArrayInputStream.available() > 0) {
                int curr = byteArrayInputStream.read();
                switch (curr) {
                    case '{', '[', '(': if(!hasContentBetween) return false; hasContentBetween = false; queue.add((char)curr); break;
                    case '}': if(!hasContentBetween || queue.isEmpty() || queue.poll() != '{') return false; hasContentBetween = false; break;
                    case ']': if(!hasContentBetween || queue.isEmpty() || queue.poll() != '[') return false; hasContentBetween = false; break;
                    case ')': if(!hasContentBetween || queue.isEmpty() || queue.poll() != '(') return false; hasContentBetween = false; break;
                    default: hasContentBetween = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue.isEmpty();
    }
}
