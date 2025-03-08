package com.Atipera_github_task;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class GitHubServiceIT {

    @Autowired
    private MockMvc mvc;
    private static final String expectedResultNotFound = """
            {
                "status": "404",
                "message": "User 'dfgddfdfsv' not found"
            }
            """;
    private static final String expectedResult = """
            [
                {
                    "reposName": "configs",
                    "ownerLogin": "user",
                    "branches": [
                        {
                            "name": "master",
                            "commit": {
                                "sha": "3cb43196cdf6129b40cd728a5d1a008d77b31f62"
                            }
                        }
                    ]
                },
                {
                    "reposName": "crux-ports",
                    "ownerLogin": "user",
                    "branches": [
                        {
                            "name": "master",
                            "commit": {
                                "sha": "2e08f0b95045c27f4ebde05a707214144654f883"
                            }
                        }
                    ]
                },
                {
                    "reposName": "dwm",
                    "ownerLogin": "user",
                    "branches": [
                        {
                            "name": "master",
                            "commit": {
                                "sha": "f1e8c93445e5c831b07eb740368042bbeb78edff"
                            }
                        }
                    ]
                },
                {
                    "reposName": "st",
                    "ownerLogin": "user",
                    "branches": [
                        {
                            "name": "master",
                            "commit": {
                                "sha": "eecf39a939dacc816d6fd9a8a732c8823b9ba362"
                            }
                        }
                    ]
                }
            ]
            """;

    @Test
    void testGettingReposInfoByUsernameOrElseThrow404() throws Exception {

        // Given
        String username = "user";
        String usernameNotFound = "dfgddfdfsv";

        // When
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/github/" + username + "/repos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Then
        assertEquals(expectedResult, result.getResponse().getContentAsString());



//        RequestBuilder request = MockMvcRequestBuilders.get("/github/user/repos");
//        MvcResult resultAction = mvc.perform(request)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        Assert.hasText(expectedResult, resultAction.getResponse().getContentAsString());
//
//        RequestBuilder requestNotFound = MockMvcRequestBuilders.get("/github/dfgddfdfsv/repos");
//        mvc.perform(requestNotFound)
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("status")
//                        .value("404"))
//                .andExpect(MockMvcResultMatchers.jsonPath("message")
//                        .value("User 'dfgddfdfsv' not found"));
    }
}