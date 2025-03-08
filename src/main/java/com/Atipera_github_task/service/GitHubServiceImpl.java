package com.Atipera_github_task.service;

import com.Atipera_github_task.dto.BranchDto;
import com.Atipera_github_task.dto.ReposInfoResponseDto;
import com.Atipera_github_task.dto.RepositoryDto;
import com.Atipera_github_task.exception.UserNotFoundException;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.converters.uni.UniReactorConverters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GitHubServiceImpl implements GitHubService {

    private static final String LIST_REPOS_BY_USERNAME_PATH = "/users/{username}/repos";
    private static final String LIST_BRANCHES_BY_REPOS_PATH = "/repos/{owner}/{repo}/branches";
    private final WebClient webClient;

    public GitHubServiceImpl(@Value("${github.api.token}") String GITHUB_API_TOKEN) {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + GITHUB_API_TOKEN)
                .baseUrl("https://api.github.com")
                .build();
    }

    public Uni<List<ReposInfoResponseDto>> listNonForkReposByUsername(String username) {

        List<RepositoryDto> repositoryDtoList =  webClient.get()
                .uri(LIST_REPOS_BY_USERNAME_PATH, username)
                .retrieve()
                .bodyToFlux(RepositoryDto.class)
                .filter(repo -> !repo.isFork())
                .collectList()
                .onErrorResume(e -> Mono.error(new UserNotFoundException(username)))
                .block();
;

        if(repositoryDtoList == null) {return Uni.createFrom().item(new ArrayList<>());}

        List<ReposInfoResponseDto> response = repositoryDtoList.stream()
                .map(
                        repositoryDto -> ReposInfoResponseDto.builder()
                                .reposName(repositoryDto.getName())
                                .ownerLogin(repositoryDto.getOwner().getLogin())
                                .branches(listReposBranches(username, repositoryDto.getName()))
                                .build())
                .toList();

        return Uni.createFrom().item(response);
    }

    public List<BranchDto> listReposBranches(String owner, String repos) {
        return webClient.get()
                .uri(LIST_BRANCHES_BY_REPOS_PATH, owner, repos)
                .retrieve()
                .bodyToFlux(BranchDto.class)
                .collectList()
                .block();
    }
}