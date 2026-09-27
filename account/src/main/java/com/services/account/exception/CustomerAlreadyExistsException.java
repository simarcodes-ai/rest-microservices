package com.services.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
