package com.Atipera_github_task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchDto {
    private String name;
    @JsonProperty("commit")
    private CommitDto lastCommit;
}
