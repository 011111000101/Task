package org.example.task.exception;

public class UserUpdateException extends RuntimeException{

    public UserUpdateException(String error) {
        super("error: " + error);
    }
}