package com.example.restapiprojectsem4.exception;

public class RequestValidException extends RuntimeException{
    public RequestValidException(String message) {
        super(message);
    }
}
