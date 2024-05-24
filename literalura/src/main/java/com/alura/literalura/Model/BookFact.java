/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.alura.literalura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookFact(@JsonAlias("id") Long id,
    @JsonAlias("title") String title,
    @JsonAlias("authors") List<AuthorFact> author,
    @JsonAlias("translators") List<AuthorFact> translators,
    @JsonAlias("subjects") List<String> subjects,
    @JsonAlias("bookshelves") List<String> bookshelves,
    @JsonAlias("languages") List<String> languages,
    @JsonAlias("copyright") Boolean copyright,
    @JsonAlias("media_type") String mediaType,
    @JsonAlias("download_count") Integer downloadCount) {

}
