package com.med.check.db.exception.exceptions;

public class BadCredentialException extends RuntimeException{
    public BadCredentialException(String msg){
        super(msg);
    }
}
