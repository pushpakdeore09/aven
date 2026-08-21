package com.aven.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException e){
        return error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException e){
        return error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    ResponseEntity<Map<String, Object>> handleUnauthorized(UnauthorizedException e){
        return error(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException e){
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .orElse("Validation Failed");
        return error(HttpStatus.BAD_REQUEST, message);
    }

    ResponseEntity<Map<String , Object>> handleGeneric(Exception e){
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() != null ? e.getMessage() : "Unexpected Error");
    }

    private ResponseEntity<Map<String, Object>> error(HttpStatus httpStatus, String message){
        return ResponseEntity.status(httpStatus).body(
                Map.of("status", httpStatus.value(),
                        "error", httpStatus.getReasonPhrase(),
                        "message", message,
                        "timestamp", Instant.now().toString())
        );
    }
}
