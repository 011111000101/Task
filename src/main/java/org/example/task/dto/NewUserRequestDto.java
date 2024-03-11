package org.example.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.task.validator.NameRestriction;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequestDto extends RequestDto{
    @NotBlank(message = "Username cannot be empty")
    @NameRestriction("Root")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3,max = 12)
    private String password;
}
