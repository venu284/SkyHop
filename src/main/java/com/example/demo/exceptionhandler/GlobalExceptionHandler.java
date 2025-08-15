package com.example.demo.exceptionhandler;

import com.example.demo.Model.Passenger;
import com.example.demo.exception.PassengerNotFoundException;
import com.example.demo.exception.ReservationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.InvalidCredentialsException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<String > handlePassengerNotFoundException(PassengerNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String > handleReservationNotFoundException(ReservationNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }



    public ResponseEntity<String> handleParseException(ParseException ex){
        return new ResponseEntity<>(ex.getMessage()+"Some problem with the date conversion", HttpStatus.BAD_REQUEST);
    }

}
