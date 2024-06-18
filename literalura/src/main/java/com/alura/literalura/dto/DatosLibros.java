package com.alura.literalura.dto;

import com.alura.literalura.dto.DatosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)


public record DatosLibros(

       @JsonAlias ("title") String titulo,
       @JsonAlias("authors") List<DatosAutor> autor,
       @JsonAlias("languages") List<String> idiomas,
       @JsonAlias ("download_count")Double numeroDeDescargas) {

}
