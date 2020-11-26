package com.gemography.repostatistics.controller;

import com.gemography.repostatistics.data.LanguageStatistic;
import com.gemography.repostatistics.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/repos")
public class RepositoryController {

    @Autowired
    RepositoryService repositoryService;

    @GetMapping("/languagestatistics")
    public  List<LanguageStatistic> getLanguageStatistics() {
        try{
            return repositoryService.getLanguageStatistic();

        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Can't calculate language statistics", e);
        }
    }
}
