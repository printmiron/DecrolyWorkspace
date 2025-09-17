package org.example.proyectometaplay;

import Model.*;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;



public class MainTest {

    private static Scanner sc = new Scanner(System.in);

    private static AccessSql BD = new AccessSql();

    public static void main(String[] args) {



        mostrarMejoresVideojuegos();



//        GeneroV[] generos = GeneroV.values();
//
//        System.out.println("Elige el genero de del videojuego:");
//        for (int i = 0; i < generos.length; i++) {
//            System.out.println((i + 1) + ". " + generos[i].name());
//        }
//
//        // Validar entrada
//        int opcion = 0;
//        boolean entradaValida = false;
//
//        while (!entradaValida) {
//            try {
//                opcion = Integer.parseInt(sc.nextLine());
//
//                if (opcion >= 1 && opcion <= generos.length) {
//                    entradaValida = true;
//                } else {
//                    System.out.printf("Por favor, introduce un número entre 1 y %d: ", generos.length);
//                }
//            } catch (NumberFormatException e) {
//                System.out.print("Entrada no válida. Por favor, introduce un número: ");
//            }
//        }
//
//        GeneroV genero = generos[opcion - 1];
//
//        // Obtener y mostrar los juegos del género seleccionado
//        List<VideoJuego> juegosDelGenero = BD.getVideoJuegosGenero(genero);
//
//        if (juegosDelGenero.isEmpty()) {
//            System.out.println("\nNo se encontraron juegos del género " + genero.name());
//        } else {
//            System.out.println("\n--- JUEGOS DE " + genero.name() + " ---");
//            for (VideoJuego juego : juegosDelGenero) {
//                System.out.println("----------------------------------");
//                System.out.println("Nombre: " + juego.getNombre());
//                System.out.println("Desarrollador: " + juego.getDesarrollador());
//                System.out.println("Precio: " + juego.getPrecio() + "€");
//                // Puedes añadir más detalles si lo necesitas
//            }
//            System.out.println("----------------------------------");
//            System.out.println("Total de juegos encontrados: " + juegosDelGenero.size());
//        }


    }
        public static void monstrarVideojuegos() {
            List<VideoJuego> videojuegos = BD.getVideojuegos();
            for (VideoJuego videojuego : videojuegos) {
                System.out.println(videojuego.toString());
            }

            if (videojuegos.isEmpty()) {
                System.out.println("No hay videojuegos");
            }

        }

    public static void mostrarMejoresVideojuegos() {
        try (Connection connection = DataBaseSql.getConnection()) {
            List<VideoJuego> videojuegos = BD.obtenerMejoresVideojuegos();

            if (videojuegos.isEmpty()) {
                System.out.println("No hay videojuegos con puntuación superior a 90");
            } else {
                System.out.println("\n--- MEJORES VIDEOJUEGOS ---");
                for (VideoJuego videojuego : videojuegos) {
                    System.out.printf("ID: %d, Nombre: %s, Puntuación Global: %.2f%n",
                            videojuego.getId(),
                            videojuego.getNombre(),
                            videojuego.getPuntuacionGlobal());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los videojuegos: " + e.getMessage());
        }
    }


    public static void monstrarUsuarios() {
        List<Usuario> usuarios = BD.getUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString());
        }

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios");
        }
    }





    private static String validarEntrada(String regex) {
        String entrada;
        Pattern pattern = Pattern.compile(regex);
        do {
            entrada = sc.nextLine().trim();
            if (!pattern.matcher(entrada).matches()) {
                System.out.println("Formato incorrecto, intenta de nuevo: ");
            }
        } while (!pattern.matcher(entrada).matches());
        return entrada;
    }




    }







