package org.example.task.restController;

import org.example.task.dto.ResponseDto;
import org.example.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends ResponseDto> getUserById(@PathVariable Long id) {
        var userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    //TODO create CRUD operations
}