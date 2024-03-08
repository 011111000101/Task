package org.example.task.restController;

import org.example.task.dto.ErrorResponseDto;
import org.example.task.exception.TableIsEmptyException;
import org.example.task.exception.UserAlreadyExistsException;
import org.example.task.exception.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponseDto handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponseDto(ex.getMessage(),"User with id is not exist.");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponseDto handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ErrorResponseDto(ex.getMessage(),"User is already exist.");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TableIsEmptyException.class)
    public ErrorResponseDto handleTableIsEmptyException(TableIsEmptyException ex) {
        return new ErrorResponseDto(ex.getMessage(),"Table is empty.");
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleValidationException(MethodArgumentNotValidException ex) {
        var errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        return new ErrorResponseDto("Validation failed", errorMessages);
    }
}