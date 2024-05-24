/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alura.literalura.Service;

import com.alura.literalura.DTO.BookDto;
import com.alura.literalura.Model.Book;
import com.alura.literalura.Repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> obtenerTodosLosLibros() {
        List<Book> libros = bookRepository.findAll();
        return convertirABookDto(libros);
    }

    public List<BookDto> buscarLibrosPorTitulo(String titulo) {
        List<Book> libros = bookRepository.findByTituloContainingIgnoreCase(titulo);
        return convertirABookDto(libros);
    }

    public List<BookDto> buscarLibrosPorIdioma(String idioma) {
        List<Book> libros = bookRepository.findByIdioma(idioma);
        return convertirABookDto(libros);
    }

    private List<BookDto> convertirABookDto(List<Book> libros) {
        return libros.stream()
                .map(this::convertirABookDto)
                .collect(Collectors.toList());
    }

    private BookDto convertirABookDto(Book libro) {
        return new BookDto(
                libro.getId(),
                libro.getTitulo(),
                libro.getIdioma(),
                libro.getNumeroDescargas(),
                libro.getAuthor().getNombre()
        );
    }
}

