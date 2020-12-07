package com.aktios.newsletters.exception;

public class ExistingSubscriptionException extends RuntimeException {
    public ExistingSubscriptionException(String error){
        super(error);
    }
}