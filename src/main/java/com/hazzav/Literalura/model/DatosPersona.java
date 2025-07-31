package com.hazzav.Literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosPersona (
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String anoNacimiento,
        @JsonAlias("death_year") String anoFallecimiento
){
}
