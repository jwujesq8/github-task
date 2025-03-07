package com.Atipera_github_task.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReposInfoResponseDto {
    private String reposName;
    private String ownerLogin;
    private List<BranchDto> branches;
}
