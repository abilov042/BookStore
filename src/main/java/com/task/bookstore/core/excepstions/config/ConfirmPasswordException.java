package com.task.bookstore.core.excepstions.config;

public class ConfirmPasswordException extends RuntimeException{
    public ConfirmPasswordException(String message){
        super(message);
    }
}
