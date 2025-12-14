package com.assignment.acksession.assignment.exception;

import com.assignment.acksession.assignment.model.ErrorResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> notFound(final HttpClientErrorException notFoundException) {

        return new ResponseEntity<>(ErrorResponseModel.builder()
                .message(notFoundException.getMessage())
                .status(notFoundException.getStatusCode().toString()).build(), HttpStatus.NOT_FOUND);
    }

}
