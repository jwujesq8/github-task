package com.Atipera_github_task.exception;

import io.smallrye.mutiny.Uni;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GitHubExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public Uni<Map<String, Object>> handleUserNotFound(UserNotFoundException e) {
        return Uni.createFrom().item(Map.of("status", 404, "message", e.getMessage()));
    }
}
