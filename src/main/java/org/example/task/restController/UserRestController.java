package org.example.task.restController;

import jakarta.validation.Valid;
import org.example.task.dto.BasicResponseDto;
import org.example.task.dto.ResponseDto;
import org.example.task.dto.UserRequestDto;
import org.example.task.dto.UserResponseDto;
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
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto updatedDto) {
        var createdUser = userService.createUser(updatedDto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<? extends ResponseDto> getUser(@RequestParam Long id) {
        var userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping()
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserRequestDto updatedDto) {
        var updatedUser = userService.updateUser(updatedDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping
    public ResponseEntity<? extends ResponseDto> deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.ok(new BasicResponseDto("User deleted with ID: " + id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        var listOfAllUsers = userService.getAllUsers();
        return ResponseEntity.ok(listOfAllUsers);
    }
}