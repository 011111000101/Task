package org.example.task.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String username) {
        super("username: " + username);
    }
}