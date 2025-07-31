package com.hazzav.Literalura.model;

import java.util.List;
import java.util.Optional;

public class Libro {
    // Long id;
    private String titulo;
    private String idioma;
    private Autor autor;
    private Double descargas;

    public Libro(DatosLibro datosLibro) {
        this.titulo = Optional.ofNullable(datosLibro.titulo()).orElse("Título no disponible");

        this.autor = Optional.ofNullable(datosLibro.autores())
                .flatMap(autores -> autores.stream()
                        .findFirst())
                .map(Autor::new)
                .orElse(null);

        this.idioma = Optional.ofNullable(datosLibro.idiomas().get(0)).orElse(null);

        this.descargas = Optional.ofNullable(datosLibro.descargas()).orElse(0.0);

    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idioma;
    }

    public void setIdiomas(String idiomas) {
        this.idioma = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return  "Titulo='" + titulo + '\'' +
                ", autores=" + autor +
                ", idiomas=" + idioma +
                ", descargas=" + descargas;
    }
}
