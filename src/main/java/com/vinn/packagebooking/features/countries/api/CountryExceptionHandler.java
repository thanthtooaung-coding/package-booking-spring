package com.vinn.packagebooking.features.countries.api;

import com.vinn.packagebooking.common.exception.InvalidFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.vinn.packagebooking.features.countries.api")
public class CountryExceptionHandler {

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidFieldException(InvalidFieldException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Validation Error");
        errorResponse.put("field", ex.getField());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Error");
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
