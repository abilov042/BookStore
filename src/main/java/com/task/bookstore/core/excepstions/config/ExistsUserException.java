package com.task.bookstore.core.excepstions.config;

public class ExistsUserException extends RuntimeException{
    public ExistsUserException(String message){
        super(message);
    }
}
