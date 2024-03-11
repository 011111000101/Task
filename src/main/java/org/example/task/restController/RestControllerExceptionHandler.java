package org.example.task.restController;

import org.example.task.dto.ErrorResponseDto;
import org.example.task.exception.UserAlreadyExistsException;
import org.example.task.exception.UserDtoIsEmpty;
import org.example.task.exception.UserIdNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserIdNotFoundException.class)
    public ErrorResponseDto handleUserNotFoundException(UserIdNotFoundException ex) {
        return new ErrorResponseDto(ex.getMessage(),"User with current id is not exist.");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ErrorResponseDto handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ErrorResponseDto(ex.getMessage(),"User is already exist.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserDtoIsEmpty.class)
    public ErrorResponseDto handleUserDtoIsEmpty(UserDtoIsEmpty ex) {
        return new ErrorResponseDto(ex.getMessage(),"Used DTO is empty.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponseDto handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ErrorResponseDto(ex.getMessage(),"Missing parameter.");
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