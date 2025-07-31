package com.hazzav.Literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Entity
//@Table(name = "autor")
public class Autor {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;
    //@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(DatosAutor datosPersona) {
        // Asigna el nombre, o "Desconocido" si es nulo.
        this.nombre = Optional.ofNullable(datosPersona.nombre())
                .orElse("Desconocido");

        // Asigna el año de nacimiento, o "Desconocido" si es nulo.
        this.anoNacimiento = Optional.ofNullable(datosPersona.anoNacimiento()).orElse(0);

        // Asigna el año de fallecimiento, o "Desconocido" si es nulo.
        this.anoFallecimiento = Optional.ofNullable(datosPersona.anoFallecimiento()).orElse(0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }

    public void setAnoFallecimiento(Integer anoFallecimiento) {
        this.anoFallecimiento = anoFallecimiento;
    }

    @Override
    public String toString() {
        return  "Nombre='" + nombre + '\'' +
                ", anoNacimiento='" + anoNacimiento + '\'' +
                ", anoFallecimiento='" + anoFallecimiento;
    }
}
