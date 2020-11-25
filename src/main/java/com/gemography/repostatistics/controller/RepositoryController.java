package com.gemography.repostatistics.controller;

import com.gemography.repostatistics.service.LanguageStatistic;
import com.gemography.repostatistics.service.RepositoryDetails;
import com.gemography.repostatistics.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/repos")
public class RepositoryController {

    @Autowired
    RepositoryService repositoryService;

    @GetMapping("/public/trendiest")
    public  List<LanguageStatistic> getTrendiestPublicRepos() {
        List<RepositoryDetails> trendiestPublicRepos = repositoryService.getTrendiestPublicRepos();
        List<LanguageStatistic> result = buildLanguageStatistic(trendiestPublicRepos);
        return result;
    }

    private List<LanguageStatistic> buildLanguageStatistic(List<RepositoryDetails> trendiestPublicRepos) {
        Map<String, List<String>> languages = new HashMap<>();
        trendiestPublicRepos.stream()
                .filter( repo -> repo.getLanguage() != null)
                .forEach(repo -> {
                            if(languages.containsKey( repo.getLanguage())) {
                                languages.get(repo.getLanguage()).add(repo.getName());
                            } else {
                                ArrayList<String> repoNames = new ArrayList<>();
                                repoNames.add(repo.getName());
                                languages.put(repo.getLanguage(), repoNames);
                            }
        } );

        return languages.entrySet().stream().map( entry -> {
            LanguageStatistic languageStatistic = new LanguageStatistic();
            languageStatistic.setName(entry.getKey());
            languageStatistic.setRepos(entry.getValue());
            languageStatistic.setCount(entry.getValue().size());
            return languageStatistic;
        }).collect(Collectors.toList());
    }
}
