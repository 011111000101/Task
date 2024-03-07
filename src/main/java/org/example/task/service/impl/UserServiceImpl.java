package org.example.task.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;
import org.example.task.model.User;
import org.example.task.repository.UserRepository;
import org.example.task.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        var savedUser = userRepository.save(user);
        return mapModelToDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        var user = userRepository.findById(id);
        return user.map(this::mapModelToDto).orElseGet(UserResponseDto::new);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto dto) {
        var user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException());

        if (!dto.getUsername().isBlank()) {
            user.setUsername(dto.getUsername());
            var userUpdated = userRepository.save(user);
            return mapModelToDto(userUpdated);
        } else {
            throw new EntityNotFoundException("Username for update is empty for user with ID: " + dto.getId());
        }
    }

    @Override
    public boolean delete(Long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id).isEmpty();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::mapModelToDto)
                .toList();
    }

    private UserResponseDto mapModelToDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername());
    }

    private void mapDtoToModel(UserResponseDto dto, User user) {
        user.setUsername(dto.getUsername());
        user.setId(dto.getId());
    }

    private void mapDtoToModel(UserRequestDto dto, User user) {
        user.setUsername(dto.getUsername());
        user.setId(dto.getId());
    }
}