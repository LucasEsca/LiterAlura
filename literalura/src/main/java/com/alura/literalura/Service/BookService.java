package com.alura.literalura.Service;

import com.alura.literalura.DTO.BookDto;
import com.alura.literalura.Model.Author;
import com.alura.literalura.Model.AuthorFact;
import com.alura.literalura.Model.Book;
import com.alura.literalura.Model.BookFact;
import com.alura.literalura.Repository.AuthorRepository;
import com.alura.literalura.Repository.BookRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ApiClient apiClient;

    public List<BookDto> obtenerTodosLosLibros() {
        List<Book> libros = bookRepository.findAll();
        return convertirABookDto(libros);
    }

    public void buscarYAlmacenarLibroPorTituloDesdeApi(String titulo) {
    List<BookFact> librosFact = apiClient.fetchBooksByTitle(titulo);
    if (librosFact.isEmpty()) {
        System.out.println("No se encontraron libros con ese título en la API.");
    } else {
        librosFact.forEach(bookFact -> {
            String tituloLibro = bookFact.title();
            if (bookRepository.findByTitulo(tituloLibro).isEmpty()) {
                Author author = mapToEntity(bookFact.authors().get(0), new HashSet<>()); // Asumiendo que solo hay un autor

                // Guardar el autor en la base de datos antes de asociarlo al libro
                authorRepository.save(author);

                Book book = mapToEntity(bookFact);
                book.setId(null); // Asegúrate de que el ID sea nulo para que se genere automáticamente
                book.setAuthor(author); // Asignar el autor al libro
                bookRepository.save(book);
            } else {
                System.out.println("El libro '" + tituloLibro + "' ya está almacenado en la base de datos.");
            }
        });
    }
}

    private Book mapToEntity(BookFact bookFact) {
        Author author = mapToEntity(bookFact.authors().get(0), new HashSet<>()); // Asumiendo que solo hay un autor

        Book book = new Book(
                bookFact.title(),
                bookFact.languages().get(0), // Asumiendo que el primer idioma es el principal
                bookFact.downloadCount(),
                author
        );

        // Agregar el libro al conjunto de libros del autor
        Set<Book> books = author.getBooks();
        if (books == null) {
            books = new HashSet<>();
            author.setBooks(books);
        }
        books.add(book);

        return book;
    }

    private Author mapToEntity(AuthorFact authorFact, Set<Book> books) {
        Author author = new Author();
        author.setNombre(authorFact.name());
        author.setAnoNacimiento(authorFact.birthYear());
        author.setAnoFallecimiento(authorFact.deathYear());
        author.setBooks(books);
        return author;
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