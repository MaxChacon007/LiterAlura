package com.alura.literalura.principal;

import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosAutor;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    public void muestraElMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        //   System.out.println(datos);

        //Top 10 libros más descargados
//        System.out.println("Top 10 libros más descargados");
//        datos.resultados().stream()
//                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
//                .limit(10)
//                .map(l -> l.titulo().toUpperCase())
//                .forEach(System.out::println);

        var opcion = -1;
        while (opcion != 0) {
            String menu = """
                    *********************************************
                                     BIENVENIDO
                     Eliga una opción, por favor
                     
                         1 Buscar libro por titulo
                         2 listar libros registrados
                         3 listar autores registrados
                         4 listar autores vivos en un determinado año
                         5 listar libros por idioma
                         0 - salir
                         
                    *********************************************
                     """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    libroPorTitulo();
                    break;
                case 2:
                    //listarLibrosRegistrados();
                    break;
                case 3:
                    //listarAutoresRegistrados();
                    break;
                case 4:
                    //AutoresVivosEnAno();
                    break;
                case 5:
                    //librosPorIdioma();
                    break;
                case 0:
                    //System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    //System.out.println("Opción inválida");
            }



        }
    }

    //Buscar libro por titulo
    private void libroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro Encontrado ");
            System.out.println(libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }
        
        
        

//        ejemplo private void buscarSerieWeb() {
//            DatosSerie datos = getDatosSerie();
//            Serie serie = new Serie(datos);
//            repositorio.save(serie);
//            //datosSeries.add(datos);
//            System.out.println(datos);            
                
                
                
    }
}
