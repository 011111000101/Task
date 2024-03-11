package org.example.task.service.impl;

import org.example.task.dto.NewUserRequestDto;
import org.example.task.dto.RequestDto;
import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;
import org.example.task.exception.UserAlreadyExistsException;
import org.example.task.exception.UserDtoIsEmpty;
import org.example.task.exception.UserIdNotFoundException;
import org.example.task.model.User;
import org.example.task.model.UserDetailModel;
import org.example.task.repository.UserRepository;
import org.example.task.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(NewUserRequestDto dto) {
        isUserExist(dto.getUsername());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        var user = mapDtoToModel(dto);
        return mapModelToDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return mapModelToDto(loadUser(id));
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto dtoUpdate) {
        isRequestDtoBlank(dtoUpdate);
        var user = loadUser(dtoUpdate.getId());
        updateFields(user, dtoUpdate);
        return mapModelToDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        loadUser(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapModelToDto).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(UserDetailModel::new).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
    }

    private void isUserExist(String username) {
        if (userRepository.existsUserByUsername(username)){
            throw new UserAlreadyExistsException(username);
        };
    }

    private void updateFields(User user, UserRequestDto dto) {
        if (!dto.getUsername().isBlank()) {
            user.setUsername(dto.getUsername());
        }

        if (!dto.getPassword().isBlank()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setPassword(dto.getPassword());
        }
    }

    private User loadUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));
    }

    private void isRequestDtoBlank(UserRequestDto dto) {
        if (dto.getUsername().isBlank() && dto.getPassword().isBlank()) {
            throw new UserDtoIsEmpty("Cannot update user.");
        }
    }

    private UserResponseDto mapModelToDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername());
    }

    private User mapDtoToModel(NewUserRequestDto dto) {
        var user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
