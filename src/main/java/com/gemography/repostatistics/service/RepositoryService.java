package com.gemography.repostatistics.service;

import com.gemography.repostatistics.data.LanguageStatistic;
import com.gemography.repostatistics.data.RepositoriesDetails;
import com.gemography.repostatistics.data.RepositoryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    public static final String DATE_FORMAT = "YYYY-MM-dd";
    public static final String BASE_URL = "https://api.github.com";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public List<RepositoryDetails> getTrendiestPublicRepos() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        DateTimeFormatter gitHubDateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        String queryDate = LocalDate.now().minusDays(30).format(gitHubDateFormatter);

        URI targetUrl= UriComponentsBuilder.fromUriString(BASE_URL)
                .path("/search/repositories")
                .queryParam("q", "created:>".concat(queryDate))
                .queryParam("sort", "stars")
                .queryParam("order", "desc")
                .queryParam("per_page", "100")
                .build()
                .encode()
                .toUri();

        RepositoriesDetails repositoriesDetails = restTemplate.getForObject(targetUrl, RepositoriesDetails.class);
        return repositoriesDetails.getRepositoryDetails();
    }


    public static List<LanguageStatistic> buildLanguageStatistic(List<RepositoryDetails> trendiestPublicRepos) {
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

    public List<LanguageStatistic> getLanguageStatistic() {
        List<RepositoryDetails> trendiestPublicRepos = getTrendiestPublicRepos();
        return buildLanguageStatistic(trendiestPublicRepos);
    }
}
