package com.Atipera_github_task;

import com.Atipera_github_task.dto.ReposInfoResponseDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GitHubServiceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGettingReposInfoByUsernameOrElseThrow404() throws Exception {

        // Given
        String username = "jwujesq8";
        String usernameNotFound = "dfgddfdfsv";

        // When
        WebTestClient.ResponseSpec responseSpec = webTestClient.get()
                .uri("/github/" + username + "/repos")
                .exchange();

        // Then
        responseSpec
                .expectStatus().isOk()
                .expectBodyList(ReposInfoResponseDto.class)
                .hasSize(4);


    }
}