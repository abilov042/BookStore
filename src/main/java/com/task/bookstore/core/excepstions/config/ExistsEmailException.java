package com.task.bookstore.core.excepstions.config;

public class ExistsEmailException extends RuntimeException{
    public ExistsEmailException(String message){
        super(message);
    }
}
