package com.pokemon.pokemon_details.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorResponse(ErrorConstants.BAD_REQUEST_CODE, ErrorConstants.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ErrorConstants.NOT_FOUND_CODE, ErrorConstants.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
