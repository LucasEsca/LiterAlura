package com.alura.literalura.Service;

import com.alura.literalura.Model.Author;
import com.alura.literalura.Repository.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> obtenerTodosLosAutores() {
        return authorRepository.findAll();
    }
    
    public List<Author> obtenerAutoresVivosPorAno(int ano) {
        return authorRepository.findByAnoFallecimientoGreaterThan(ano);
    }
    
}