/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.literalura.Principal;

import com.alura.literalura.DTO.BookDto;
import com.alura.literalura.Service.BookService;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Principal implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Buscar libros por idioma");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume el salto de línea

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo(scanner);
                    break;
                case 2:
                    listarTodosLosLibros();
                    break;
                case 3:
                    buscarLibrosPorIdioma(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida, intenta de nuevo.");
            }
        }
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();
        List<BookDto> libros = bookService.buscarLibrosPorTitulo(titulo);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarTodosLosLibros() {
        List<BookDto> libros = bookService.obtenerTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros almacenados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void buscarLibrosPorIdioma(Scanner scanner) {
        System.out.println("Ingrese el idioma del libro:");
        String idioma = scanner.nextLine();
        List<BookDto> libros = bookService.buscarLibrosPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
        } else {
            libros.forEach(System.out::println);
        }
    }
}
