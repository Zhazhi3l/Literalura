package com.hazzav.Literalura.repositorio;

import com.hazzav.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNombreContainingIgnoreCase(String nombre);
}
