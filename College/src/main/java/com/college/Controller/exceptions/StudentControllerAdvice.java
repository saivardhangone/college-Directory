package com.college.Controller.exceptions;

import com.college.Entity.StudentEntity;
import com.college.Exception.StudentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StudentControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<String> noStudentFound(StudentNotFound exception,  WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> generalError(Exception exception,  WebRequest request){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
