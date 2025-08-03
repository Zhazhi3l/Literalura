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

    public void buscarLibroWeb() {
        System.out.println("Escriba el titulo del libro que desea buscar:");
        String tituloLibro = sc.nextLine();

        // Validación de existencia
        Optional<Libro> libroExistente =
                libroRepository.findLibroByTituloContainingIgnoreCase(tituloLibro);
        if (libroExistente.isPresent()) {
            System.out.println("Ya estaba registrado el libro: " + libroExistente.get());
            return;
        }

        //  Consumir de la API
        var json = consumoAPI.obtenerDatos(
                URL_BASE + BUSQUEDA + tituloLibro.replace(" ", "%20"));
        RespuestaAPI respuesta = conversor.obtenerDatos(json, RespuestaAPI.class);
        //System.out.println(respuesta); // prueba

        //Primer libro e informar
        Optional<DatosLibro> libroEncontrado = respuesta.resultados()
                .stream()
                .findFirst();

        if (libroEncontrado.isPresent()) {
            System.out.println("Resultado encontrado: " + libroEncontrado.get());
        } else {
            System.out.println("No se encontraron resultados.");
            return;
        }
        DatosLibro datosLibro = libroEncontrado.get();

        // Obtener autor
        Optional<DatosAutor> datosAutor = datosLibro.autores()
                .stream()
                .findFirst();

        // Creacion autor
        Autor autor = datosAutor
                .map(autorService::obtenerOCrearAutor)
                .orElseGet(
                        () -> autorService.obtenerOCrearAutor(
                                new DatosAutor("Desconocido", 0, 0)));

        // Crear librillo y guardar junto con Autor
        Libro libro = new Libro(datosLibro);
        autor.addLibro(libro);
        autorService.guardarAutor(autor);

        System.out.println("Libro registrado: " + libro);
    }
}
