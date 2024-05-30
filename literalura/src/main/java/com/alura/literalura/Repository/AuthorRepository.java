package com.alura.literalura.Repository;

import com.alura.literalura.Model.Author;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByAnoNacimientoBeforeAndAnoFallecimientoAfter(int anoNacimiento, int anoFallecimiento);  
    List<Author> findAll();
    List<Author> findByAnoFallecimientoGreaterThan(int anoFallecimiento);
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.nombre = :nombre")
    Optional<Author> findByNombreWithBooks(@Param("nombre") String nombre);
}