package com.college.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason ="No Such Student")
public class StudentNotFound  extends RuntimeException{
    public StudentNotFound(String message){
        super(message);
    }
}
