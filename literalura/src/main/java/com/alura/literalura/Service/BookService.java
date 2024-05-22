/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alura.literalura.Service;

import com.alura.literalura.Model.Book;
import com.alura.literalura.Repository.BookRepository;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

    public List<Book> fetchAndSaveBooks() {
        try {
            List<Book> books = apiClient.fetchBooks().get();
            bookRepository.saveAll(books);
            return books;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}