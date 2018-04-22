package com.myrecipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistingUser extends RuntimeException
{
    public ExistingUser(String message)
    {
        super(message);
    }
}
