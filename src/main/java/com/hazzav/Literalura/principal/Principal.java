package com.hazzav.Literalura.principal;

import com.hazzav.Literalura.model.DatosLibro;
import com.hazzav.Literalura.model.RespuestaAPI;
import com.hazzav.Literalura.service.ConsumoAPI;
import com.hazzav.Literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private static final String BUSQUEDA = "?search=";

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    --- MENÚ LITERALURA ---
                    1 - Buscar libro por titulo 
                    
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
                    buscarLibroPorTitulo();

                    break;
                case 0:
                    System.out.println("Cerrando aplicación... Gracias. :)");
                    break;
                default:
                    System.out.println("Escoja una opción válida.");
            }
        }
    }

    private RespuestaAPI obtenerRespuestaAPI() {
        System.out.println("Escriba el titulo del libro que desea buscar:");
        String tituloLibro = sc.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + BUSQUEDA + tituloLibro.replace(" ","%20"));
        RespuestaAPI respuesta = conversor.obtenerDatos(json, RespuestaAPI.class);
        System.out.println(respuesta); // prueba
        return respuesta;
    }

    private void buscarLibroPorTitulo() {
        Optional<DatosLibro> libroBuscado = obtenerRespuestaAPI().resultados().stream()
                .findFirst();

        System.out.println(libroBuscado.orElse(null));
    }
}
