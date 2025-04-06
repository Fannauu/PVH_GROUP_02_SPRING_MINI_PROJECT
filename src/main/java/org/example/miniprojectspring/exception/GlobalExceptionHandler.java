package org.example.miniprojectspring.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.example.miniprojectspring.model.dto.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation on DTO fields with @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return buildResponse("Validation failed", HttpStatus.BAD_REQUEST, errors);
    }

    // Handle @Validated on method parameters
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(v ->
                errors.put(v.getPropertyPath().toString(), v.getMessage())
        );

        return buildResponse("Constraint violation", HttpStatus.BAD_REQUEST, errors);
    }

    // Handle illegal arguments (e.g. pagination)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    // Handle not found exceptions
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(NotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    // Username not found
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUsernameNotFound(UsernameNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    // Handle null pointer
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse<Object>> handleNullPointer(NullPointerException ex) {
        log.error("NullPointerException", ex);
        return buildResponse("Unexpected null value occurred", HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    // Handle bad credentials (login)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials(BadCredentialsException ex) {
        return buildResponse("Invalid username or password", HttpStatus.UNAUTHORIZED, null);
    }

    // Handle DB constraint violations
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        return buildResponse("Database constraint violated: " + ex.getMostSpecificCause().getMessage(), HttpStatus.CONFLICT, null);
    }

    // andle file too large
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<Object>> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        return buildResponse("File size exceeds limit!", HttpStatus.PAYLOAD_TOO_LARGE, null);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> handleUnauthorized(HttpClientErrorException.Unauthorized ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "status", 401,
                        "error", "Unauthorized",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // Optional: Catch any other 401 manually thrown
    @ExceptionHandler(value = { AccessDeniedException.class, AuthenticationException.class })
    public ResponseEntity<?> handleAccessDenied(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "status", 401,
                        "error", "Unauthorized",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

//     Catch all
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> handleAllOtherExceptions(Exception ex) {
//        log.error("Unhandled exception occurred", ex);
//        return buildResponse("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
//    }

    // Utility method for building consistent response
    private ResponseEntity<ApiResponse<Object>> buildResponse(String message, HttpStatus status, Object payload) {
        return ResponseEntity.status(status).body(
                ApiResponse.builder()
                        .success(false)
                        .message(message)
                        .payload(payload)
                        .httpStatus(status)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}
