package com.hazzav.Literalura.repositorio;

import com.hazzav.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findLibroByTituloContainingIgnoreCase(String titulo);
    Optional<List<Libro>> findByIdioma(String codigoIdioma);
}
