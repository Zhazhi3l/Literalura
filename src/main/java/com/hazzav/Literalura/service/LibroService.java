package com.hazzav.Literalura.service;

import com.hazzav.Literalura.model.*;
import com.hazzav.Literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorService autorService;

    private ConsumoAPI consumoAPI;
    private ConvierteDatos conversor;
    private Scanner sc = new Scanner(System.in);

    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA = "?search=";

    public void buscarLibroWeb(){
        System.out.println("Escriba el titulo del libro que desea buscar:");
        String tituloLibro = sc.nextLine();

        // Validación de existencia
        Optional<Libro> libroExistente = libroRepository.findLibroByTituloContainingIgnoreCase(tituloLibro);
        if(libroExistente.isPresent()){
            System.out.println("Ya estaba registrado el libro: " + libroExistente.get());
            return;
        }

        var json = consumoAPI.obtenerDatos(URL_BASE + BUSQUEDA + tituloLibro.replace(" ","%20"));
        RespuestaAPI respuesta = conversor.obtenerDatos(json, RespuestaAPI.class);
        //System.out.println(respuesta); // prueba

        Optional<DatosLibro> datosLibro = respuesta.resultados()
                .stream()
                .findFirst();

        Optional<DatosAutor> datosAutor = datosLibro
                .flatMap(libro -> libro.autores()
                        .stream()
                        .findFirst());

        Autor autor = datosAutor
                .map(autorService::obtenerOCrearAutor);

        Libro libro = new Libro(datosLibro.get());
        libro.setAutor(autor);
    }
}
