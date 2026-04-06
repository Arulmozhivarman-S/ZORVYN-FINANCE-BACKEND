package com.arul.finance_backend.exceptions;

public class InvalidAccessException extends RuntimeException {

    public InvalidAccessException(String message){
        super(message);
    }
    
}
