package com.alura.literalura.Principal;

import com.alura.literalura.Service.BookService;
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
            System.out.println("4. Buscar y almacenar libro por título desde la API");
            System.out.println("5. Salir");

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
                    buscarYAlmacenarLibroPorTituloDesdeApi(scanner);
                    break;
                case 5:
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
        // Lógica para listar todos los libros
    }

    private void buscarLibrosPorIdioma(Scanner scanner) {
        System.out.println("Ingrese el idioma del libro:");
        String idioma = scanner.nextLine();
        // Lógica para buscar libros por idioma
    }

    private void buscarYAlmacenarLibroPorTituloDesdeApi(Scanner scanner) {
        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();
        bookService.buscarYAlmacenarLibroPorTituloDesdeApi(titulo);
    }
}