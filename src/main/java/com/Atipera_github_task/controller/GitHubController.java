package com.Atipera_github_task.controller;

import com.Atipera_github_task.dto.ReposInfoResponseDto;
import com.Atipera_github_task.dto.RepositoryDto;
import com.Atipera_github_task.service.GitHubService;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public Uni<List<ReposInfoResponseDto>> getRepositories(@PathVariable String username) {
        return gitHubService.listNonForkReposByUsername(username);
    }
}
