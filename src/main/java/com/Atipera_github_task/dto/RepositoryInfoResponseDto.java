package com.Atipera_github_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RepositoryInfoResponseDto {
    private String reposName;
    private String ownerLogin;
    private List<BranchDto> branches;
}
