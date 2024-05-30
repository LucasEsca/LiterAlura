package com.alura.literalura.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Book> books;
    
    // Getters, Setters, Constructor, equals, hashCode y toString

    public Author() {
    }

    public Author(String nombre, Integer anoNacimiento, Integer anoFallecimiento, Set<Book> books) {
        this.nombre = nombre;
        this.anoNacimiento = anoNacimiento;
        this.anoFallecimiento = anoFallecimiento;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.anoNacimiento);
        hash = 59 * hash + Objects.hashCode(this.anoFallecimiento);
        hash = 59 * hash + Objects.hashCode(this.books);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.anoNacimiento, other.anoNacimiento)) {
            return false;
        }
        if (!Objects.equals(this.anoFallecimiento, other.anoFallecimiento)) {
            return false;
        }
        return Objects.equals(this.books, other.books);
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", nombre=" + nombre + ", anoNacimiento=" + anoNacimiento + ", anoFallecimiento=" + anoFallecimiento + ", books=" + books + '}';
    }
    
}
