package org.example.task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.task.validator.NameRestriction;
import org.example.task.validator.NullOrSize;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto extends RequestDto{
    @NotNull
    private Long id;
    @NameRestriction("Root")
    private String username;
    @NullOrSize(min = 3, max = 12)
    private String password;
}