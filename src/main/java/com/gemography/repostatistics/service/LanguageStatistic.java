package com.gemography.repostatistics.service;

import lombok.Data;

import java.util.List;

@Data
public class LanguageStatistic {
    private String name;
    private List<String> repos;
    private int count;
}
