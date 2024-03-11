package org.example.task.exception;

public class UserDtoIsEmpty extends RuntimeException{

    public UserDtoIsEmpty(String message) {
        super(message);
    }
}