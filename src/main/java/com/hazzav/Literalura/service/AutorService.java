package com.hazzav.Literalura.service;

import com.hazzav.Literalura.model.Autor;
import com.hazzav.Literalura.model.DatosAutor;
import com.hazzav.Literalura.repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;

    public Autor obtenerOCrearAutor(DatosAutor datosAutor) {
        String nombre = Optional.ofNullable(datosAutor.nombre())
                .orElse("Desconocido");

        Autor autor = autorRepository
                .findByNombreContainingIgnoreCase(nombre);

        if (autor != null) {
            return autor;
        }
        return autorRepository.save(new Autor(datosAutor));
    }

    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> obtenerAutoresVivos(int ano){
        Optional<List<Autor>> listaAutoresVivos = autorRepository.findByAnoNacimientoLessThanEqualAndAnoFallecimientoGreaterThanEqual(ano, ano);
        if(listaAutoresVivos.isPresent()){
            return listaAutoresVivos.get();
        }else{
            System.out.println("No se encontraron autores vivos en el año: " + ano);
            return null;
        }
    }
}
