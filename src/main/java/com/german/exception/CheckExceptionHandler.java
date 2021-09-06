package com.german.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.german.exception.CheckExceptionMessages.UNKNOWN_EXCEPTION;

@ControllerAdvice
public class CheckExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({CheckException.class})
    public ResponseEntity<CheckException> handleMarketplaceException(CheckException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                new CheckException(UNKNOWN_EXCEPTION),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
