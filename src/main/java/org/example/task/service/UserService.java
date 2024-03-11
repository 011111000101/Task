package org.example.task.service;

import org.example.task.dto.NewUserRequestDto;
import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(NewUserRequestDto dto);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(UserRequestDto dto);
    void delete(Long id);
    List<UserResponseDto> getAllUsers();
}