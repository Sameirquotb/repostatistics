package com.gemography.repostatistics.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RepositoriesDetails {
    @JsonProperty("items")
    private List<RepositoryDetails> repositoryDetails;
}
