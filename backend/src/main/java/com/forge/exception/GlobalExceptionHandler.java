package com.forge.exception;

import com.forge.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ApiResponse<>(
                false,
                "Validation failed",
                errors
        );
    }@ExceptionHandler(ResourceNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public ApiResponse<String> handleResourceNotFoundException(
        ResourceNotFoundException ex) {

    return new ApiResponse<>(
            false,
            ex.getMessage(),
            null
    );
}
@ExceptionHandler(RuntimeException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ApiResponse<String> handleRuntimeException(RuntimeException ex){

    return new ApiResponse<>(
            false,
            ex.getMessage(),
            null
    );
}
}