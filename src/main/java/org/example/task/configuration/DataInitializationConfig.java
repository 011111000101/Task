package org.example.task.configuration;

import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;
import org.example.task.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DataInitializationConfig implements CommandLineRunner {

    private final UserService userService;

    public DataInitializationConfig(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public void run(String... args) {
        userService.createUser(new UserRequestDto("admin"));
        userService.createUser(new UserRequestDto("Karel Vopršálek"));
    }
}