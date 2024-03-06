package org.example.task.service;

import org.example.task.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto updateUser(UserDto userDto);
    void delete(Long id);
}