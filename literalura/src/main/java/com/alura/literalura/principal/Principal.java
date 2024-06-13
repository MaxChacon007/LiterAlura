package com.alura.literalura.principal;

import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE ="https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private Scanner teclado =new Scanner(System.in);

        public void muestraElMenu(){
            var json = consumoAPI.obtenerDatos(URL_BASE);
            System.out.println(json);
            var datos = conversor.obtenerDatos(json, Datos.class);
            //System.out.println(datos);

    //Busqueda de libros por nombre - OK

            System.out.println("Ingrese el nombre del libro que desea buscar");
            var tituloLibro = teclado.nextLine();
            json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
            var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
            Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                    .findFirst();
            if(libroBuscado.isPresent()){
                System.out.println("Libro Encontrado ");
                System.out.println("Titulo: "+libroBuscado.get().titulo());
                System.out.println("Autor: "+libroBuscado.get().autor().get(0).nombre());
                System.out.println("Idioma: "+libroBuscado.get().idiomas());
                System.out.println("Numero de descargas: "+libroBuscado.get().numeroDeDescargas());
            }else {
                System.out.println("Libro no encontrado");
            }


        }




}
