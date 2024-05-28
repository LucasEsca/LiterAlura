package com.alura.literalura.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;


@Service
public class ConsumoApi {

    private final HttpClient httpClient;

    public ConsumoApi() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String obtenerDatos(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos de la API: " + e.getMessage(), e);
        }
    }
}