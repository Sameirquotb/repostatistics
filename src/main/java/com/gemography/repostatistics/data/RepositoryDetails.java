package com.gemography.repostatistics.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RepositoryDetails {
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    private String language;
}
