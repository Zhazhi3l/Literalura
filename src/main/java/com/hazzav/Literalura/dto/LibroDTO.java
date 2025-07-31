package com.hazzav.Literalura.dto;

import com.hazzav.Literalura.model.Autor;

import java.util.List;

public record LibroDTO(
         String titulo,
         List<Autor>autores,
         List<String> resumenes,
         List<Autor> traductores,
         List<String> categorias,
         List<String> idiomas,
         Integer descargas
) {
}