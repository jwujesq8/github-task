package com.Atipera_github_task.service;

import com.Atipera_github_task.dto.BranchDto;
import com.Atipera_github_task.dto.OwnerDto;
import com.Atipera_github_task.dto.ReposInfoResponseDto;
import com.Atipera_github_task.dto.RepositoryDto;
import io.smallrye.mutiny.Uni;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GitHubService {

    Uni<List<ReposInfoResponseDto>> listNonForkReposByUsername(String username);
    List<BranchDto> listReposBranches(String owner, String repos);
}
