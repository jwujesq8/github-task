package com.Atipera_github_task.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {
    private String status;
    private String message;

}
