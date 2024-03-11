package org.example.task.restController;

import jakarta.validation.Valid;
import org.example.task.dto.*;
import org.example.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody NewUserRequestDto updatedDto) {
        var createdUser = userService.createUser(updatedDto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<? extends ResponseDto> getUser(@RequestParam Long id) {
        var userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping()
    public ResponseEntity<? extends ResponseDto> updateUser(@Valid @RequestBody UserRequestDto updatedDto) {
        userService.updateUser(updatedDto);
        return ResponseEntity.ok(new BasicResponseDto("User changed."));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        var listOfAllUsers = userService.getAllUsers();
        return ResponseEntity.ok(listOfAllUsers);
    }
}