package org.example.task.restController;

import org.example.task.dto.BasicResponseDto;
import org.example.task.dto.ResponseDto;
import org.example.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping
    public ResponseEntity<? extends ResponseDto> deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return ResponseEntity.ok(new BasicResponseDto("User deleted with ID: " + id));
    }
}