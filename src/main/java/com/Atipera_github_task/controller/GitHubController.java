package com.Atipera_github_task.controller;

import com.Atipera_github_task.dto.RepositoryInfoResponseDto;
import com.Atipera_github_task.service.GitHubService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GitHubController {

    private final GitHubService gitHubService;

    @GetMapping("/{username}/repos")
    public Uni<List<RepositoryInfoResponseDto>> getRepositories(@PathVariable String username) {
        return gitHubService.listNonForkReposByUsername(username);
    }
}
