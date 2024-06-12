package com.alura.literalura;

import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication  implements  CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {//esto sale de la Clase consumo API,
	var consumoApi = new ConsumoAPI();
	var	json = consumoApi.obtenerDatos("https://gutendex.com/books/");
		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		 var datos= conversor.obtenerDatos(json, DatosLibros.class);
		//System.out.println(datos);

		var	json1 = consumoApi.obtenerDatos("https://gutendex.com/books/");
		DatosLibros datosLibros = conversor.obtenerDatos(json1,DatosLibros.class);
		System.out.println(datosLibros);
	}
}
