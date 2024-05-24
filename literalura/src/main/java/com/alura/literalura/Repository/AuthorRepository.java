/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alura.literalura.Repository;

import com.alura.literalura.Model.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Usuario
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByAnoNacimientoBeforeAndAnoFallecimientoAfter(int anoNacimiento, int anoFallecimiento);  
}
