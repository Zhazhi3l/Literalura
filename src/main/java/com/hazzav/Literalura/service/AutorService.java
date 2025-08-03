package com.hazzav.Literalura.service;

import com.hazzav.Literalura.model.Autor;
import com.hazzav.Literalura.model.DatosAutor;
import com.hazzav.Literalura.repositorio.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;

    public Autor obtenerOCrearAutor(DatosAutor datosAutor) {
        String nombre = Optional.ofNullable(datosAutor.nombre())
                .orElse("Autor desconocido");

        Autor autor =  autorRepository.findByNombreContaining(nombre);

        if(autor != null)
            return autor;
        else
            return autorRepository.save(new Autor(datosAutor));
    }

}
