package com.aluracursos.buscar_musica;

import com.aluracursos.buscar_musica.principal.Principal;
import com.aluracursos.buscar_musica.repositorio.CantantesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuscarMusicaApplication implements CommandLineRunner {

	@Autowired
	private CantantesRepositorio repositorio;
	public static void main(String[] args) {
		SpringApplication.run(BuscarMusicaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.ejecutarPrograma();
	}
}
