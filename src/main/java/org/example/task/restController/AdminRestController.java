package org.example.task.restController;

import org.example.task.dto.BasicResponseDto;
import org.example.task.dto.ResponseDto;
import org.example.task.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AdminRestController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }
    @DeleteMapping
    public ResponseEntity<? extends ResponseDto> deleteUser(@RequestParam Long id, @RequestHeader HttpHeaders headers) {
        userService.delete(id);
        return ResponseEntity.ok(new BasicResponseDto("User deleted with ID: " + id));
    }
}