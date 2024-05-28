package com.alura.literalura.Repository;

import com.alura.literalura.Model.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByAnoNacimientoBeforeAndAnoFallecimientoAfter(int anoNacimiento, int anoFallecimiento);  
}
