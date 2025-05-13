package com.aluracursos.buscar_musica.repositorio;

import com.aluracursos.buscar_musica.modelos.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CantantesRepositorio extends JpaRepository<Cantante, Long> {
    Optional<Cantante> findByNombreContainsIgnoreCase(String nombre);

}
