/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.literalura.Service;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConvierteDatos implements IConvierteDatos {
    
    private final ObjectMapper objectMapper;

    public ConvierteDatos(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        if (json.isEmpty()) {
            return null; // Retorna null si la respuesta JSON está vacía
        }
        try {
            return objectMapper.readValue(json, clase);
        } catch(JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }          
}

