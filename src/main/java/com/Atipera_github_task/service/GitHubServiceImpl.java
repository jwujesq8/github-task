package com.Atipera_github_task.service;

import com.Atipera_github_task.dto.BranchDto;
import com.Atipera_github_task.dto.ReposInfoResponseDto;
import com.Atipera_github_task.dto.RepositoryDto;
import com.Atipera_github_task.exception.UserNotFoundException;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitHubServiceImpl implements GitHubService {

    private static final String LIST_REPOS_BY_USERNAME_PATH = "/users/{username}/repos";
    private static final String LIST_BRANCHES_BY_REPOS_PATH = "/repos/{owner}/{repo}/branches";
    private final WebClient webClient = WebClient.builder().baseUrl("https://api.github.com").build();;

    public Uni<List<ReposInfoResponseDto>> listNonForkReposByUsername(String username) {
        List<RepositoryDto> repositoryDtoList =  webClient.get()
                .uri(LIST_REPOS_BY_USERNAME_PATH, username)
                .retrieve()
                .bodyToFlux(RepositoryDto.class)
                .filter(repo -> !repo.isFork())
                .collectList()
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.error(new UserNotFoundException(username)))
                .block();

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


    public List<BranchDto> listReposBranches(String owner, String repos){

        return webClient.get()
                .uri(LIST_BRANCHES_BY_REPOS_PATH, owner, repos)
                .retrieve()
                .bodyToFlux(BranchDto.class)
                .collectList()
                .block();
    }
}
