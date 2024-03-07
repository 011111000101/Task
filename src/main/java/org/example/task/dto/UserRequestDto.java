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
public class UserRequestDto extends RequestDto{
    private Long id;
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    public UserRequestDto(String username) {
        this.username = username;
    }
}