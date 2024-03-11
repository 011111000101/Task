package org.example.task.exception;

public class UserIdNotFoundException extends RuntimeException{

    public UserIdNotFoundException(Long id) {
        super("id: " + id);
    }
}