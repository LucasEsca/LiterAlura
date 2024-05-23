/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alura.literalura.Service;

import com.alura.literalura.Model.Book;
import com.alura.literalura.Repository.BookRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ApiClient apiClient;

    @Autowired
    public BookService(BookRepository bookRepository, ApiClient apiClient) {
        this.bookRepository = bookRepository;
        this.apiClient = apiClient;
    }

    public List<Book> getAllBooks() {
        // Simplemente obtenemos todos los libros de la base de datos utilizando el repositorio.
        return bookRepository.findAll();
    }
    
    public Book searchBookByTitle(String title) {
        // Buscamos un libro en la base de datos por su título.
        return bookRepository.findByTitle(title).stream().findFirst().orElse(null);
    }

    public List<Book> fetchAndSaveBooks(String title) {
    // Primero, buscamos libros en la base de datos por título.
    List<Book> booksFromDb = bookRepository.findByTitle(title);
    
    // Retornamos los libros encontrados en la base de datos.
    return booksFromDb;
}

}

