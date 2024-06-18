package com.alura.literalura.principal;

import com.alura.literalura.dto.Datos;
import com.alura.literalura.dto.DatosAutor;
import com.alura.literalura.dto.DatosLibros;
import com.alura.literalura.model.MapAutor;
import com.alura.literalura.model.MapLibros;
import com.alura.literalura.repository.MapLibrosRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE ="https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private Scanner teclado =new Scanner(System.in);
    private MapLibrosRepository repositorio;
    public Principal(MapLibrosRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
            var json = consumoAPI.obtenerDatos(URL_BASE);
            var datos = conversor.obtenerDatos(json, Datos.class);
            System.out.println(json);
            System.out.println(datos);

            var opcion = -1;
            while (opcion != 0) {
                var menu = """ 
                        ***********************************************
                        1 - Buscar libro por titulo
                        2 - listar libros registrados
                        3 - listar autores registrados
                        4 - listar autores vivos en un determinado año
                        5 - listar libros por idioma
                                                           
                        0 - Salir
                        ***********************************************
                        """;
                System.out.println(menu);
                System.out.print("Ingrese su opción: ");

                // Verificar si la entrada es un número válido
                while (!teclado.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número del 0 al 5 según las indicaciones.");
                    System.out.print("Ingrese su opción: ");
                    teclado.next();
                }
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                       //listarLibroRegistrados();
                        break;
                    case 3:
                        //listarAutoresRegistrados();
                        break;
                    case 4:
                        //listarAutoresVivosPorAno();
                        break;
                    case 5:
                        //listarLibroporIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("\"Ingresó una opción inválida. Por favor, ingrese un número del 0 al 5 según las indicaciones.\"");
                }
            }
        }



    //     1 Buscar libro por titulo
                private  Datos buscarLibroPorTitulo() {
                    System.out.println("Ingrese el nombre del libro que desea buscar");
                    var tituloLibro = teclado.nextLine();
                    String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
                    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
                    Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                            .findFirst();


                    if (libroBuscado.isPresent()) {
                        System.out.println("Libro Encontrado ");
                        System.out.println("Titulo: " + libroBuscado.get().titulo());
                        System.out.println("Autor: " + libroBuscado.get().autor().get(0).nombre());
                        System.out.println("Idioma: " + libroBuscado.get().idiomas());
                        System.out.println("Numero de descargas: " + libroBuscado.get().numeroDeDescargas());

                        // Guardar el libro encontrado en la base de datos
                        MapLibros libro = new MapLibros();
                        libro.setTitulo(libroBuscado.get().titulo());
                        libro.setIdiomas(libroBuscado.get().idiomas().toString());
                        libro.setNumeroDeDescargas(libroBuscado.get().numeroDeDescargas());

                        // Obtener o crear el autor y relacionarlo con el libro
                        List<MapAutor> autores = new ArrayList<>();
                        DatosAutor autor = libroBuscado.get().autor().get(0);  // Suponiendo un solo autor por simplicidad
                        MapAutor mapAutor = new MapAutor();
                        mapAutor.setNombre(autor.nombre());
                        // Setear otras propiedades del autor si es necesario
                        autores.add(mapAutor);

                        libro.setAutores(autores);

                        // Guardar en la base de datos utilizando el repositorio
                        repositorio.save(libro);

                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    return datosBusqueda;
                }}



                //inyeccion de dependecias



//            2 listar libros registrados en postgress

//                    private void listarLibroRegistrados() {
//                        System.out.println("se espera codgio = ";
//                        var librosRegistrados = teclado.nextLine();
//                        String json1 = consumoAPI.obtenerDatos(URL_BASE + "?search=" + librosRegistrados.replace(" ", "+"));
//                        var datosBusquedaDB = conversor.obtenerDatos(json1, Datos.class);
//



//            3 listar autores registrados en postgress

//            4 listar autores vivos en un determinado año

//            System.out.println("Ingrese el año vivo de autor(es) que se desea buscar");
//            var fecha = teclado.nextLine();
//            teclado.nextLine();

//            5 listar libros por idioma








