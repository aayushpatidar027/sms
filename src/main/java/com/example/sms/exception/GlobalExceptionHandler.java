package com.example.sms.exception;

import com.example.sms.dto.ErrorResponse;
import com.example.sms.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
        // You can add methods here to handle specific exceptions
        // For example, to handle a custom exception:
        /*
        @ExceptionHandler(CustomException.class)
        public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
            ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        */
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistException(AlreadyExistException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
    }

}
