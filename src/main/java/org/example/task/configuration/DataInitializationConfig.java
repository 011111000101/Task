package org.example.task.configuration;

import org.example.task.dto.UserDto;
import org.example.task.model.User;
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
        userService.createUser(new UserDto("admin"));
        userService.createUser(new UserDto("Karel Vopršálek"));
    }
}