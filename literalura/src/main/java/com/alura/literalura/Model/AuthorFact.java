package com.alura.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorFact(
    @JsonAlias("name") String name,
    @JsonAlias("birth_year") Integer birthYear,
    @JsonAlias("death_year") Integer deathYear) {
}
