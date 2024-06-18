package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "datos_autores")
public class MapAutor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String nombre;

        private String fechaDeNacimiento;
        private String fechaDeFallecimiento;

        @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
        private List<MapLibros> libros = new ArrayList<>();

        // Getters y setters
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public String getFechaDeNacimiento() {
                return fechaDeNacimiento;
        }

        public void setFechaDeNacimiento(String fechaDeNacimiento) {
                this.fechaDeNacimiento = fechaDeNacimiento;
        }

        public String getFechaDeFallecimiento() {
                return fechaDeFallecimiento;
        }

        public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
                this.fechaDeFallecimiento = fechaDeFallecimiento;
        }

        public List<MapLibros> getLibros() {
                return libros;
        }

        public void setLibros(List<MapLibros> libros) {
                this.libros = libros;
        }
}
