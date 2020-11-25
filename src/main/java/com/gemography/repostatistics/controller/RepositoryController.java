package com.gemography.repostatistics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/repos")
public class RepositoryController {

    @GetMapping("/public/trendiest")
    public Map<String, Integer> getTrendiestPublicRepos() {
        Map<String,Integer> repos = new HashMap<>();
        repos.put("repo1", 10);
        repos.put("repo2", 15);
        return repos;
    }
}
