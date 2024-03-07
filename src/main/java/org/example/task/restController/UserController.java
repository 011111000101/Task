package org.example.task.restController;

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
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<? extends ResponseDto> getUser(@RequestParam Long id) {
        var userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping
    public ResponseEntity<? extends ResponseDto> deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.ok(new BasicResponseDto("User deleted with ID: " + id));
    }

    @PatchMapping()
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto updatedDto) {
        var updatedUser = userService.updateUser(updatedDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto updatedDto) {
        var createdUser = userService.createUser(updatedDto);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> createUser() {
        var listOfAllUsers = userService.getAllUsers();
        return ResponseEntity.ok(listOfAllUsers);
    }

}