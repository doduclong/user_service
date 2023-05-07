package com.example.user_service.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id) {
        super("Could not find endEntity " + id);
    }
}
