package com.alura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.literalura.model.MapLibros;
public interface MapLibrosRepository extends JpaRepository<MapLibros,Long > {
}
