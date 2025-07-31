package com.hazzav.Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro (
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Persona>autores,
        @JsonAlias("summaries") List<String> resumenes,
        @JsonAlias("translators") List<Persona> traductores,
        @JsonAlias("subjects") List<String> categorias,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer descargas
){
}
