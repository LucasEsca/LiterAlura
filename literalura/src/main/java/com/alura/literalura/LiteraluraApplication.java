package com.alura.literalura;

import com.alura.literalura.Model.Book;
import com.alura.literalura.Service.BookService;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.alura.literalura")
public class LiteraluraApplication implements CommandLineRunner {

    private final BookService bookService;

    @Autowired
    public LiteraluraApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Insertar Libros desde Gutendex");
            System.out.println("2. Listar Libros de la Base de Datos");
            System.out.println("3. Salir");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1:
                    // Consultar libro por título en Gutendex y almacenarlo en la DB
                    System.out.println("Ingrese el título del libro que desea buscar en Gutendex:");
                    String titleToSearch = scanner.nextLine();
                    List<Book> booksFound = bookService.fetchAndSaveBooks(titleToSearch);
                    if (!booksFound.isEmpty()) {
                        System.out.println("Libros encontrados y almacenados:");
                        booksFound.forEach(book -> System.out.println(book.getTitle()));
                    } else {
                        System.out.println("No se encontraron libros en Gutendex con el título ingresado.");
                    }
                    break;
                case 2:
                    // Listar todos los libros almacenados en la DB
                    List<Book> allBooks = bookService.getAllBooks();
                    if (!allBooks.isEmpty()) {
                        System.out.println("Lista de todos los libros en la Base de Datos:");
                        allBooks.forEach(book -> System.out.println(book.getTitle()));
                    } else {
                        System.out.println("No hay libros almacenados en la Base de Datos.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}


