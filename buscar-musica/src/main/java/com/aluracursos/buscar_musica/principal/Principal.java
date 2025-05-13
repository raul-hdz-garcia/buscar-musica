package com.aluracursos.buscar_musica.principal;

import com.aluracursos.buscar_musica.repositorio.CantantesRepositorio;
import com.aluracursos.buscar_musica.modelos.Cancion;
import com.aluracursos.buscar_musica.modelos.Cantante;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private CantantesRepositorio repositorio;
    private int opcion = -1;
    private final String menu = """
            ************** MENÚ **************
            1 - Registrar datos de cantantes
            2 - Registrar datos de canciones
            3 - Buscar canciones por cantante
            
            0 - Salir
            **********************************
            """;
    private Scanner teclado = new Scanner(System.in);

    public Principal(CantantesRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutarPrograma() {
        while (opcion != 0) {
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    registrarDatosCantantes();
                    break;
                case 2:
                    registrarDatosCanciones();
                    break;
                case 3:
                    buscarCancionesPorCantante();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida\n");
            }
        }
    }

    public void registrarDatosCantantes() {
        System.out.println("Ingrese el nombre del cantante que desea registrar");
        var nombre = teclado.nextLine();
        Cantante cantante = new Cantante(nombre);
        repositorio.save(cantante);
        System.out.println();
    }

    private Optional<Cantante> buscarCantanteEnLista() {
        System.out.println("Escoja un cantante de la lista:");
        List<Cantante> listaCantantes = repositorio.findAll();
        listaCantantes.forEach(System.out::println);
        var nombre = teclado.nextLine();
        return repositorio.findByNombreContainsIgnoreCase(nombre);
    }

    public void registrarDatosCanciones() {
        Optional<Cantante> cantante = buscarCantanteEnLista();
        if (cantante.isPresent()) {
            System.out.println("Ingrese una canción de " + cantante.get());
            var cancion = teclado.nextLine();
            Cancion titulo = new Cancion(cancion);
            titulo.setCantante(cantante.get());
            cantante.get().getListaCanciones().add(titulo);
            repositorio.save(cantante.get());
            System.out.println();
        } else {
            System.out.println("Cantante no registrado\n");
        }
    }

    public void buscarCancionesPorCantante() {
        Optional<Cantante> cantante = buscarCantanteEnLista();
        if (cantante.isPresent()) {
            System.out.println("Canciones registradas de " + cantante.get() + ":");
            if (cantante.get().getListaCanciones().isEmpty()) {
                System.out.println("No hay canciones registradas");
            } else {
                cantante.get().getListaCanciones().forEach(System.out::println);
            }
            System.out.println();
        } else {
            System.out.println("Cantante no registrado\n");
        }
    }
}