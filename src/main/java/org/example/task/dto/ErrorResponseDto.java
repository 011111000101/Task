package org.example.task.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponseDto extends ResponseDto{
    private final String status;
    private final String message;

    public ErrorResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
