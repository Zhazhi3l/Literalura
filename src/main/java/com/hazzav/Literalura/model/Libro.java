package com.hazzav.Literalura.model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double descargas;
    @ManyToOne
    private Autor autor;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = Optional.ofNullable(datosLibro.titulo()).orElse("Título no disponible");

        this.autor = getAutor(datosLibro);

        this.idioma = getPrimerIdioma(datosLibro);

        this.descargas = Optional.ofNullable(datosLibro.descargas()).orElse(0.0);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
