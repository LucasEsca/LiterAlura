/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.literalura.Service;

import com.alura.literalura.Model.Book;
import com.alura.literalura.Model.GutendexResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ApiClient {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static final String BASE_URL = "https://gutendex.com";
    
    @Autowired
    public ApiClient(ObjectMapper objectMapper) {
        this.httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        this.objectMapper = objectMapper;
    }

    public List<Book> fetchBooksByTitle(String title) {
        String url = BASE_URL + "/books?search=" + title.replace(" ", "%20");
        return fetchBooksByUrl(url).join();
    }

    private CompletableFuture<List<Book>> fetchBooksByUrl(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(this::handleResponse)
                .exceptionally(this::handleException);
    }

    private List<Book> handleResponse(HttpResponse<String> httpResponse) {
        if (httpResponse.statusCode() == 200) {
            String responseBody = httpResponse.body();
            if (responseBody.isEmpty()) {
                return List.of(); // Return an empty list if response body is empty
            }
            try {
                GutendexResponse response = objectMapper.readValue(responseBody, GutendexResponse.class);
                return response.getResults();
            } catch (Exception e) {
                throw new RuntimeException("Error parsing response body", e);
            }
        } else {
            throw new RuntimeException("Unexpected HTTP status code: " + httpResponse.statusCode());
        }
    }

    private List<Book> handleException(Throwable throwable) {
        throwable.printStackTrace();
        return List.of();
    }
}
