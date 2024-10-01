package com.college.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class FacultyException extends RuntimeException{
    public FacultyException(String message){
        super(message);
    }
}
