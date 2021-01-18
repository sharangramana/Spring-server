package com.example.Server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingMobileNumException.class)
    public ResponseEntity<?> missingMobileNumberException(MissingMobileNumException exp, WebRequest webRequest) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(), exp.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyPresentException.class)
    public ResponseEntity<?> userAlreadyPresentException(UserAlreadyPresentException exp, WebRequest webRequest) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(), exp.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(SomethingWentWrongException.class)
    public ResponseEntity<?> somethingWentWrongException(SomethingWentWrongException exp, WebRequest webRequest) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(), exp.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException exp, WebRequest webRequest) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(), exp.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
    }
}
