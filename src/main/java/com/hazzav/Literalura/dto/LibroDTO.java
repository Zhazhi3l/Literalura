package com.hazzav.Literalura.dto;

import com.hazzav.Literalura.model.Autor;

import java.util.List;

public record LibroDTO(
         String titulo,
         Autor autor,
         String idioma,
         Double descargas
) {
}