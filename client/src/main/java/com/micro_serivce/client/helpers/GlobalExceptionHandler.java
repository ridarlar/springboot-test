package com.micro_serivce.client.helpers;

import com.micro_serivce.client.helpers.CustomExceptions.CreationFailedException;
import com.micro_serivce.client.helpers.CustomExceptions.DuplicateDataException;
import com.micro_serivce.client.helpers.CustomExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "The resource was not found or does not exist",
                "RESOURCE_NOT_FOUND"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateDataException(DuplicateDataException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "Are param is duplicated in another reguister",
                "RESOURCE_DUPLICATED"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CreationFailedException.class)
    public ResponseEntity<ErrorResponse> handleCreationFailed(CreationFailedException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                "Was not create the register",
                "CREATION_FAILED"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
