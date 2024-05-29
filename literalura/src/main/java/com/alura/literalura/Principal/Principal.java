package com.alura.literalura.Principal;

import com.alura.literalura.Model.Author;
import com.alura.literalura.Model.Book;
import com.alura.literalura.Service.AuthorService;
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
    @Autowired
    private AuthorService authorService;
    
    private static final int MAX_TITLE_LENGTH = 255;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar todos los libros registrados");
            System.out.println("3. Listar todos los autores registrados");
            System.out.println("4. Listar Autores vivos por año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("0. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el título del libro:");
                    String titulo = scanner.nextLine();
                    if (titulo.length() > MAX_TITLE_LENGTH) {
                        System.out.println("El título es demasiado largo. Por favor ingrese un título de hasta " + MAX_TITLE_LENGTH + " caracteres.");
                    } else {
                        Book book = bookService.buscarYAlmacenarLibroPorTituloDesdeApi(titulo);
                        if (book != null) {
                            mostrarDetallesDelLibro(book);
                        } else {
                            System.out.println("No se almacenó ningún libro nuevo.");
                        }
                    }
                    break;
                case 2:
                    listarTodosLosLibros();
                    break;
                case 3:
                    listarTodosLosAutores();
                    break;
                case 4:
                    listarAutoresVivos(scanner);
                    break;
                case 5:
                    buscarLibrosPorIdioma(scanner);
                    break;
                case 6:
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
        // Lógica para buscar libro por título
    }

    private void listarTodosLosLibros() {
        List<Book> libros = bookService.obtenerTodosLosLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados:");
            for (Book libro : libros) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAuthor().getNombre());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Número de descargas: " + libro.getNumeroDescargas());
                System.out.println("----------");
            }
        }
    }
    
    private void listarTodosLosAutores() {
         List<Author> autores = authorService.obtenerTodosLosAutores();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            for (Author autor : autores) {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Año de nacimiento: " + (autor.getAnoNacimiento() != null ? autor.getAnoNacimiento() : "N/A"));
                System.out.println("Año de fallecimiento: " + (autor.getAnoFallecimiento() != null ? autor.getAnoFallecimiento() : "N/A"));
                System.out.println("----------");
            }
        }
    }
    
    private void listarAutoresVivos(Scanner scanner) {
        System.out.println("Ingrese el año para listar autores vivos:");
        int year = scanner.nextInt();
        List<Author> autoresVivos = authorService.obtenerAutoresVivosPorAno(year);
        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos registrados después del año " + year + ".");
        } else {
            System.out.println("Autores vivos registrados después del año " + year + ":");
        for (Author autor : autoresVivos) {
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Año de nacimiento: " + (autor.getAnoNacimiento() != null ? autor.getAnoNacimiento() : "N/A"));
            System.out.println("Año de fallecimiento: " + (autor.getAnoFallecimiento() != null ? autor.getAnoFallecimiento() : "N/A"));
            System.out.println("----------");
            }
        }
    }

    private void buscarLibrosPorIdioma(Scanner scanner) {
    List<Book> librosPorIdioma = bookService.buscarLibrosPorIdioma(scanner);

    if (librosPorIdioma.isEmpty()) {
        System.out.println("No se encontraron libros en el idioma seleccionado.");
    } else {
        System.out.println("Libros en el idioma seleccionado:");
        for (Book libro : librosPorIdioma) {
            mostrarDetallesDelLibro(libro);
            System.out.println("--------------------");
        }
    }
}

    private void buscarYAlmacenarLibroPorTituloDesdeApi(Scanner scanner) {
        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();
        bookService.buscarYAlmacenarLibroPorTituloDesdeApi(titulo);
    }
    
    private void mostrarDetallesDelLibro(Book book) {
        System.out.println("Detalles del libro almacenado:");
        System.out.println("Título: " + book.getTitulo());
        System.out.println("Autor: " + book.getAuthor().getNombre());
        System.out.println("Idioma: " + book.getIdioma());
        System.out.println("Número de descargas: " + book.getNumeroDescargas());
    }
}