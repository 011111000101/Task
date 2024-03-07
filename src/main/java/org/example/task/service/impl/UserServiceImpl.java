package org.example.task.service.impl;

import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;
import org.example.task.exception.UserAlreadyExistsException;
import org.example.task.exception.UserNotFoundException;
import org.example.task.exception.UserUpdateException;
import org.example.task.model.User;
import org.example.task.repository.UserRepository;
import org.example.task.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        var user = new User();
        mapDtoToModel(dto, user);
        if (isUserExist(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        }
        var savedUser = userRepository.save(user);
        return mapModelToDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return user.map(this::mapModelToDto).orElseGet(UserResponseDto::new);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto dtoUpdate) {
        var user = userRepository
                .findById(dtoUpdate.getId())
                .orElseThrow(() -> new UserNotFoundException(dtoUpdate.getId()));

        if (!dtoUpdate.getUsername().isBlank()) {
            user.setUsername(dtoUpdate.getUsername());
            var userUpdated = userRepository.save(user);
            return mapModelToDto(userUpdated);
        } else {
            throw new UserUpdateException("User name is missing.");
        }
    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username);
    }

    private UserResponseDto mapModelToDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername());
    }

    private void mapDtoToModel(UserRequestDto dto, User user) {
        user.setUsername(dto.getUsername());
        user.setId(dto.getId());
    }
}