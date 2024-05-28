package com.alura.literalura.Repository;

import com.alura.literalura.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTituloContainingIgnoreCase(String titulo);
    List<Book> findByIdioma(String idioma);
    List<Book> findByTitulo(String titulo);
}