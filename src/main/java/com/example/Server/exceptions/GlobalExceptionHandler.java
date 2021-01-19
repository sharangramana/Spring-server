package com.example.Server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidException(Exception e, WebRequest webRequest) {
        //String[] str = e.getMessage().split("]]; default message ");
        //String response = str[1].replaceAll("\\[", "").replaceAll("\\]","");

        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(e.getMessage());
        String response = "";
        while (matcher.find()) {
            response = matcher.group(1);
        }
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(), response, webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<?> emailNotValidException(EmailNotValidException exp, WebRequest webRequest) {
        ExceptionDetail exceptionDetail = new ExceptionDetail(new Date(), exp.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_ACCEPTABLE);
    }
}
