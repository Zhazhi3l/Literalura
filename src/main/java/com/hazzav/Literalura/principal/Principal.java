package com.hazzav.Literalura.principal;

import com.hazzav.Literalura.model.*;
import com.hazzav.Literalura.repositorio.AutorRepository;
import com.hazzav.Literalura.repositorio.LibroRepository;
import com.hazzav.Literalura.service.AutorService;
import com.hazzav.Literalura.service.ConsumoAPI;
import com.hazzav.Literalura.service.ConvierteDatos;
import com.hazzav.Literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private AutorRepository autorRepository;
    private AutorService servicioAutor;
    private LibroRepository libroRepository;
    private LibroService servicioLibro;

    private List<Libro> libros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private final String URL_BASE = "https://gutendex.com/books/";
    private final String BUSQUEDA = "?search=";


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository, AutorService autorService, LibroService libroService) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.servicioAutor = autorService;
        this.servicioLibro = libroService;
    }


    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    --- MENÚ LITERALURA ---
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    -----------------------""";
            System.out.println(menu);
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("La opción debe ser un número entero.");
                sc.nextLine();
                opcion = -1;
            }

            switch (opcion){
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;

                case 5:
                    buscarLibrosPorIdioma();
                case 0:
                    System.out.println("Cerrando aplicación... Gracias. :)");
                    break;
                default:
                    System.out.println("Escoja una opción válida.");
            }
        }
    }

    private void buscarLibrosPorIdioma() {
        String menu = ("""
                -----------------------------------
                Inserte el idioma de los libros que desee buscar:
                1 - Español
                2 - Inglés
                3 - Portugués
                -----------------------------------
                """);
        int opcionIdioma = 0;
        while (opcionIdioma < 1 || opcionIdioma > 3) {
            try {
                System.out.println(menu);
                opcionIdioma = sc.nextInt();
                sc.nextLine();
                if (opcionIdioma >= 1 && opcionIdioma <= 3) break;
                else System.out.println("Por favor, seleccione una de las opciones disponibles.");
            } catch (InputMismatchException e) {
                System.out.println("La opción debe ser un número entero.");
                sc.nextLine();
                opcionIdioma = 0;
            }
        }

        Optional<List<Libro>> librosIdiomados;
        switch (opcionIdioma){
            case 1:
                librosIdiomados = libroRepository.findByIdioma("es");
                if (librosIdiomados.isPresent()) {
                    librosIdiomados.get().forEach(System.out::println);
                }else{
                    System.out.println("No se encontraron resultados en ese idioma.");
                }
                break;
            case 2:
                librosIdiomados = libroRepository.findByIdioma("en");
                if (librosIdiomados.isPresent()) {
                    librosIdiomados.get().forEach(System.out::println);
                }else{
                    System.out.println("No se encontraron resultados en ese idioma.");
                }
                break;
            case 3:
                librosIdiomados = libroRepository.findByIdioma("pt");
                if (librosIdiomados.isPresent()) {
                    librosIdiomados.get().forEach(System.out::println);
                }else{
                    System.out.println("No se encontraron resultados en ese idioma.");
                }
                break;
            default: System.out.println("Algo salio mal.");
        }


    }

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
                .map(servicioAutor::obtenerOCrearAutor)
                .orElseGet(
                        () -> servicioAutor.obtenerOCrearAutor(
                                new DatosAutor("Desconocido", 0, 0)));

        // Crear librillo y guardar junto con Autor
        Libro libro = new Libro(datosLibro);
        autor.addLibro(libro);
        servicioAutor.guardarAutor(autor);

        System.out.println("Libro registrado: " + libro);
    }

    private void listarLibrosRegistrados(){
        List<Libro> series = libroRepository.findAll();

        series.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();

        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }
}
