package com.Atipera_github_task.service;

import com.Atipera_github_task.dto.BranchDto;
import com.Atipera_github_task.dto.RepositoryInfoResponseDto;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface GitHubService {

    Uni<List<RepositoryInfoResponseDto>> listNonForkReposByUsername(String username);
    List<BranchDto> listReposBranches(String owner, String repos);
}
