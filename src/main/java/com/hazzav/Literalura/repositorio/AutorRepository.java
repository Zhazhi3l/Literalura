package com.hazzav.Literalura.repositorio;

import com.hazzav.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNombreContainingIgnoreCase(String nombre);
    Optional<List<Autor>> findByAnoNacimientoLessThanEqualAndAnoFallecimientoGreaterThanEqual(int anoNacimiento, int anoFallecimiento);
}
