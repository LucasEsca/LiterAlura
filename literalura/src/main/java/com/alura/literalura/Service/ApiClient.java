/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.literalura.Service;

import com.alura.literalura.Model.Book;
import com.alura.literalura.Model.GutendexResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class ApiClient {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiClient() {
        this.httpClient = HttpClient.newBuilder()
                                    .version(HttpClient.Version.HTTP_2)
                                    .build();
        this.objectMapper = new ObjectMapper();
    }

    public CompletableFuture<List<Book>> fetchBooks() {
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create("https://gutendex.com/books"))
                                         .GET()
                                         .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body)
                         .thenApply(this::parseBooks);
    }

    private List<Book> parseBooks(String responseBody) {
        try {
            GutendexResponse response = objectMapper.readValue(responseBody, GutendexResponse.class);
            return response.getResults();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
    
}
