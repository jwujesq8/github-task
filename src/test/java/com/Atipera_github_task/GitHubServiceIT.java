package com.Atipera_github_task;

import com.Atipera_github_task.dto.ReposInfoResponseDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitHubServiceIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testGettingReposInfoByUsername() {

        // Given
        String username = "jwujesq8";

        // When and Then
        webTestClient.get()
                .uri("/github/" + username +"/repos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ReposInfoResponseDto.class)
                .consumeWith(response -> {
                    List<ReposInfoResponseDto> repos = response.getResponseBody();
                    System.out.println("Response: " + repos);
                    assertNotNull(repos);
                    assertFalse(repos.isEmpty());
                    assertEquals("expectedRepoName", repos.get(0).getReposName());
                });
    }
}
