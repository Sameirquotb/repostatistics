package com.gemography.repostatistics.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RepositoriesDetails {
    @JsonProperty("items")
    private List<RepositoryDetails> repositoryDetails;
}
