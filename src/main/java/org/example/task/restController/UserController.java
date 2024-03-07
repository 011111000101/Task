package org.example.task.restController;

import org.example.task.dto.ResponseDto;
import org.example.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<? extends ResponseDto> getUserById(@RequestParam Long id) {
        var userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/test")
    public ResponseEntity<? extends ResponseDto> test() {
        var userDto = userService.getUserById(1L);
        return ResponseEntity.ok(userDto);
    }
}