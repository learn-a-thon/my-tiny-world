package com.maetdori.ottention.search.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Tv {
    private String posterPath;
    private double popularity;
    private int id;
    private String backdropPath;
    private double voteAverage;
    private String overview;
    private String firstAirDate;
    private List<String> originCountry;
    private List<Integer> genreIds;
    private String originalLanguage;
    private double voteCount;
    private String name;
    private String originalName;
}
