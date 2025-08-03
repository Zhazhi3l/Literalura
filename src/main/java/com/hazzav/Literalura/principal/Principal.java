package com.hazzav.Literalura.principal;

import com.hazzav.Literalura.model.Autor;
import com.hazzav.Literalura.model.DatosLibro;
import com.hazzav.Literalura.model.Libro;
import com.hazzav.Literalura.model.RespuestaAPI;
import com.hazzav.Literalura.repositorio.AutorRepository;
import com.hazzav.Literalura.repositorio.LibroRepository;
import com.hazzav.Literalura.service.AutorService;
import com.hazzav.Literalura.service.ConsumoAPI;
import com.hazzav.Literalura.service.ConvierteDatos;
import com.hazzav.Literalura.service.LibroService;

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

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    --- MENÚ LITERALURA ---
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 -
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
                    servicioLibro.buscarLibroWeb();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación... Gracias. :)");
                    break;
                default:
                    System.out.println("Escoja una opción válida.");
            }
        }
    }


    /*
    private void buscarLibroPorTitulo() {
        Optional<DatosLibro> libroBuscado = obtenerRespuestaAPI()
                .resultados().stream()
                .findFirst();

        if (libroBuscado.isPresent()){
            //libros.add(new Libro(libroBuscado.get()));
            //autores.add(new Autor(libroBuscado.get().autores().get(0)));

            Libro librillo = new Libro(libroBuscado.get());
            libroRepository.save(librillo);

            System.out.println("Resultado encontrado: "
                + libroBuscado.get().titulo()
                + ", Autor: "
                + libroBuscado.get().autores().get(0));
        } else {
            System.out.println("No se encontró el libro.");
        }
    }*/


    private void listarLibrosRegistrados(){
        if (!libros.isEmpty())
            libros.stream()
                    .forEach(System.out::println);
                    //.forEach(libro -> System.out.println(libro + "\n" + libro.getAutor()));
        else
            System.out.println("No hay libros registrados.");
    }

    private void listarAutoresRegistrados(){
        if(!autores.isEmpty()){
            autores.stream()
                    .forEach(System.out::println);
        }else{
            System.out.println("No hay autores registrados.");
        }
    }
}
