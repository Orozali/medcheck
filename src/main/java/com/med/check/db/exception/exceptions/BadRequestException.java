package com.med.check.db.exception.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
