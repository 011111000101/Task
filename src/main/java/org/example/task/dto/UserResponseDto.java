package org.example.task.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto extends ResponseDto{
    private Long id;
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    public UserResponseDto(String username) {
        this.username = username;
    }
}