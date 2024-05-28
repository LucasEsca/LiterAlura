package com.alura.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookFact(
    @JsonAlias("id") Long id,
    @JsonAlias("title") String title,
    @JsonAlias("authors") List<AuthorFact> authors,
    @JsonAlias("languages") List<String> languages,
    @JsonAlias("download_count") Integer downloadCount) {
}
