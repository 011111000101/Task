package org.example.task.restController;

import org.example.task.dto.ErrorResponseDto;
import org.example.task.exception.UserUpdateException;
import org.example.task.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDto handleUserIdNotExistException(UserNotFoundException ex) {
        var errorResponse = new ErrorResponseDto(ex.getMessage(),"Id is not exist.");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserUpdateException.class)
    public ErrorResponseDto handleUserIdNotExistException(UserUpdateException ex) {
        var errorResponse = new ErrorResponseDto(ex.getMessage(),"UserName is empty");
        return errorResponse;
    }
}