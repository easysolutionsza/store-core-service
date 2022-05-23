package org.chargenet.store.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Data Validation Error: {}", ex.getMessage());
        String errorMessage = ex.getLocalizedMessage();
        errorMessage = "Incorrect data Input";

        ErrorDetails errorDetails = new ErrorDetails("API-101", LocalDateTime.now(), errorMessage, ex.getBindingResult().toString(), "");
        return new ResponseEntity<>(errorDetails, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<?> dataNotFoundException(DataNotFoundException ex, WebRequest request) {
        log.error("Data Not Found Error: {}", ex.getMessage());
        String errorMessage = ex.getLocalizedMessage();
        if (errorMessage == null) errorMessage = ex.toString();

        ErrorDetails errorDetails = new ErrorDetails("API-102", LocalDateTime.now(), ex.getMessage(), request.getDescription(false), "");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataConflictException.class)
    public final ResponseEntity<?> dataConflictException(DataConflictException ex, WebRequest request) {
        log.error("Data Conflict Error: {}", ex.getMessage());
        String errorMessage = ex.getLocalizedMessage();
        if (errorMessage == null) errorMessage = ex.toString();

        ErrorDetails errorDetails = new ErrorDetails("API-103", LocalDateTime.now(), ex.getMessage(), request.getDescription(false), "");
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("Internal Server Error: {}", ex.getMessage());
        String errorMessage = ex.getLocalizedMessage();
        if (errorMessage == null) errorMessage = ex.toString();

        //ErrorDetails errorDetails = new ErrorDetails("API-104", LocalDateTime.now(), ex.getMessage(), request.getDescription(false), "");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
