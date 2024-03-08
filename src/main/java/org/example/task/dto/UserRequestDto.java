package org.example.task.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.task.validator.annotation.NameRestriction;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto extends RequestDto{
    private Long id;
    @NotBlank(message = "Username cannot be empty")
    @NameRestriction("Root")
    private String username;

    public UserRequestDto(String username) {
        this.username = username;
    }
}