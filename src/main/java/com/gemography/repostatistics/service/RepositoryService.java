package com.gemography.repostatistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RepositoryService {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public List<RepositoryDetails> getTrendiestPublicRepos() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RepositoriesDetails repositoriesDetails = restTemplate.getForObject("https://api.github.com/search/repositories?q=created:>2020-11-11&sort=stars&order=desc", RepositoriesDetails.class);
        return repositoriesDetails.getRepositoryDetails();
    }
}
