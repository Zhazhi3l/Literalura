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

        this.autor = getAutor(datosLibro);

        this.idioma = getPrimerIdioma(datosLibro);

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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    private Autor getAutor(DatosLibro datosLibro){
        return Optional.ofNullable(datosLibro.autores())
                .flatMap(autores -> autores.stream()
                        .findFirst())
                .map(Autor::new)
                .orElse(null);
    }

    private String getPrimerIdioma(DatosLibro datosLibro){
        return Optional.ofNullable(datosLibro.idiomas())
                .filter(l -> !l.isEmpty())
                .map(idioma -> idioma.get(0))
                .orElse("Desconocido.");
    }

    @Override
    public String toString() {
        return  "Titulo='" + titulo + '\'' +
                ", Autor=" + autor +
                ", Idiomas=" + idioma +
                ", Descargas=" + descargas;
    }
}
