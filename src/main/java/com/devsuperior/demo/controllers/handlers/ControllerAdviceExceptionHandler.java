package com.devsuperior.demo.controllers.handlers;

import com.devsuperior.demo.dto.errors.CustomErrorResponse;
import com.devsuperior.demo.services.exceptions.DatabaseIntegrityException;
import com.devsuperior.demo.services.exceptions.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<CustomErrorResponse> entityNotFoundExceptionHandler(ResourceNotFound ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorResponse err = getCustomError(status, ex.getMessage(), request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseIntegrityException.class)
    public ResponseEntity<CustomErrorResponse> databaseIntegrityExceptionHandler(DatabaseIntegrityException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse err = getCustomError(status, ex.getMessage(), request);
        return ResponseEntity.status(status).body(err);
    }


    private static CustomErrorResponse getCustomError(HttpStatus status, String ex, HttpServletRequest request) {
        return new CustomErrorResponse(
                OffsetDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex,
                request.getRequestURI()
        );
    }
}
