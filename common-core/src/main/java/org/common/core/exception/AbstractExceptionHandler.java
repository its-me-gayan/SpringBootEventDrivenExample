package org.common.core.exception;

import jakarta.persistence.EntityNotFoundException;
import org.common.core.dto.common.AbstractResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@ControllerAdvice
public class AbstractExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AbstractResponse> handleEntityNotFoundException(Exception exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                AbstractResponse.builder()
                        .message(exception.getMessage())
                        .message_description(exception.getMessage())
                        .status_code(HttpStatus.BAD_REQUEST.value())
                        .message_code("E-001")
                        .timestamp(Timestamp.from(Instant.now()))
                        .isSuccess(Boolean.FALSE)
                        .status(HttpStatus.BAD_REQUEST).build()
        );
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<AbstractResponse> handleServiceUnavailableException(Exception exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                AbstractResponse.builder()
                        .message(exception.getMessage())
                        .message_description(exception.getMessage())
                        .status_code(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .message_code("E-002")
                        .timestamp(Timestamp.from(Instant.now()))
                        .isSuccess(Boolean.FALSE)
                        .status(HttpStatus.SERVICE_UNAVAILABLE).build()
        );
    }
    @ExceptionHandler(ServiceErrorException.class)
    public ResponseEntity<AbstractResponse> handleServiceErrorException(Exception exception){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                AbstractResponse.builder()
                        .message(exception.getMessage())
                        .message_description(exception.getMessage())
                        .status_code(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .message_code("E-002")
                        .timestamp(Timestamp.from(Instant.now()))
                        .isSuccess(Boolean.FALSE)
                        .status(HttpStatus.SERVICE_UNAVAILABLE).build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AbstractResponse> handleException(Exception exception){
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                AbstractResponse.builder()
                        .message(exception.getMessage())
                        .message_description(exception.getMessage())
                        .status_code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message_code("E-999")
                        .timestamp(Timestamp.from(Instant.now()))
                        .isSuccess(Boolean.FALSE)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        );
    }
}
