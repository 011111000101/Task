package org.example.task.service;

import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(UserRequestDto dto);
    boolean delete(Long id);
    List<UserResponseDto> getAllUsers();
}