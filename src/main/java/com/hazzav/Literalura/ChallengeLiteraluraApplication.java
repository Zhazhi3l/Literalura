package com.hazzav.Literalura;

import com.hazzav.Literalura.principal.Principal;
import com.hazzav.Literalura.repositorio.AutorRepository;
import com.hazzav.Literalura.repositorio.LibroRepository;
import com.hazzav.Literalura.service.AutorService;
import com.hazzav.Literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {
	@Autowired
	LibroRepository libroRepository;
	@Autowired
	AutorRepository autorRepository;
	@Autowired
	LibroService servicioLibro;
	@Autowired
	AutorService servicioAutor;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository, servicioAutor, servicioLibro);
		principal.muestraElMenu();
	}

}
