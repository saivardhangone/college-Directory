package com.college.Controller.exceptions;

import com.college.Entity.FacultyEntity;
import com.college.Exception.FacultyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class FacultyControllerAdvice   {
    @ExceptionHandler(FacultyException.class)
    public ResponseEntity<String>  nofaculty(FacultyException exception, WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
}

