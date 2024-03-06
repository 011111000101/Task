package org.example.task.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.task.dto.UserDto;
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
    public UserDto createUser(UserDto userDto) {
        var user = new User();
        mapDtoToModel(userDto, user);
        var savedUser = userRepository.save(user);
        return mapModelToDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id);
        return user.map(this::mapModelToDto).orElseGet(UserDto::new);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        var user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + userDto.getId()));

        if (!userDto.getUsername().isBlank()) {
            user.setUsername(userDto.getUsername());
            var userUpdated = userRepository.save(user);
            return mapModelToDto(userUpdated);
        } else {
            throw new EntityNotFoundException("Username for update is empty for user with ID: " + userDto.getId());
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapModelToDto(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }

    private void mapDtoToModel(UserDto userDto, User user) {
        user.setUsername(userDto.getUsername());
        user.setId(userDto.getId());
    }
}