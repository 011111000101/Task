package org.example.task.service;

import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto getUserById(Long id);
    UserResponseDto updateUser(UserRequestDto dto);
    void delete(Long id);
    boolean isUserExist(String username);
    List<UserResponseDto> getAllUsers();
}