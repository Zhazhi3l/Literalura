package com.hazzav.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class Libro {
    // Long id;
    private String titulo;
    private List<Persona> autores;
    private List<String> resumenes;
    private List<Persona> traductores;
    private List<String> categorias;
    private List<String> idiomas;
    private Integer descargas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Persona> getAutores() {
        return autores;
    }

    public void setAutores(List<Persona> autores) {
        this.autores = autores;
    }

    public List<String> getResumenes() {
        return resumenes;
    }

    public void setResumenes(List<String> resumenes) {
        this.resumenes = resumenes;
    }

    public List<Persona> getTraductores() {
        return traductores;
    }

    public void setTraductores(List<Persona> traductores) {
        this.traductores = traductores;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return  "Titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", resumenes=" + resumenes +
                ", traductores=" + traductores +
                ", categorias=" + categorias +
                ", idiomas=" + idiomas +
                ", descargas=" + descargas;
    }
}
