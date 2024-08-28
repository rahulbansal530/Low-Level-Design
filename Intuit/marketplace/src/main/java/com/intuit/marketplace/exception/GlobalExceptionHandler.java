package com.intuit.marketplace.exception;

import com.intuit.marketplace.dto.response.APIError;
import com.intuit.marketplace.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        APIError apiError = new APIError("VALIDATION_ERROR", "Validation failed for one or more fields.");
        APIResponse<Map<String, String>> response = new APIResponse<>(apiError, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<APIResponse<Object>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncorrectPriceException.class)
    public ResponseEntity<APIResponse<Object>> handleIncorrectPriceException(IncorrectPriceException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDateException.class)
    public ResponseEntity<APIResponse<Object>> handleIncorrectDateException(IncorrectDateException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JobClosedException.class)
    public ResponseEntity<APIResponse<Object>> handleJobClosedException(JobClosedException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongOwnerException.class)
    public ResponseEntity<APIResponse<Object>> handleWrongOwnerException(WrongOwnerException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JobPosterBidException.class)
    public ResponseEntity<APIResponse<Object>> handleJobPosterBidException(JobPosterBidException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BidNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleBidNotFoundException(BidNotFoundException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleUserNotFoundException(UserNotFoundException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleUserAlreadyExistsException(JobNotFoundException ex) {
        APIError apiError = new APIError(ex.getErrorCode(), ex.getMessage());
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        APIError apiError = new APIError("INVALID_REQUEST", "Invalid value provided in request");
        APIResponse<Object> response = new APIResponse<>(apiError, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}