package com.med.check.db.exception.exceptions;

public class AuthenticationFailException extends RuntimeException{
    public AuthenticationFailException(String msg){
        super(msg);
    }
}
