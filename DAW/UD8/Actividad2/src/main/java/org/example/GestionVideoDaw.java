package org.example;
//

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GestionVideoDaw {

    private static Scanner sc = new Scanner(System.in);

    private static AccessDCuniverseSQL BD = new AccessDCuniverseSQL();

    private static List<VideoDaw> videoclubs = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Articulo> articulos = new ArrayList<>();



    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);


//------------------------------------------------------------
//MENU PRINCIPAL



        String opcion = null;
        do {
            System.out.println("1) Crear y registrar VideoClub en la franquicia");
            System.out.println("2) Registrar alrticulo (pelicula o videojuego) en videoclub");
            System.out.println("3) Crear y registrar cliente en videoclub");
            System.out.println("4) Alquilar");
            System.out.println("5) Devolver");
            System.out.println("6) Dar de baja cliente");
            System.out.println("7) Dar de baja articulo");
            System.out.println("8) Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarVideoClub(videoclubs); //v
                    break;

                case "2":
                    registrarArticulo(); //v
                    break;

                case "3":
                    registrarCliente(); //v
                    break;

                case "4":
                    alquilarArticulo(); //v
                    break;

                case "5":
                    devolverArticulo(); //v
                    break;

                case "6":
                    darBajaCliente(); //main cor
                    break;

                case "7":
                    darBajaArticulo(); //main cor
                    break;

                case "8":
                    System.out.println("Hasta luego!");

                    break;
                default:
                    System.out.println("Opcion no valido!");
            }

        } while (!"8".equals(opcion));

    }














//------------------------------------------------------------
//METODOS PARA REGISTRAR VIDEOCLUB, ARTICULOS Y CLIENTES











    //registro videoclub
    public static void registrarVideoClub(List<VideoDaw> franquicia) {

        System.out.println("Introduce el CIF del Videoclub (Ej: A12345678): ");
        String cif = validarEntrada("[A-HJNP-SUVW][0-9]{8}");

        System.out.println("Introduce la direccion del Vieoclub: ");
        String direccion = sc.nextLine();

        LocalDate fechaAlta = LocalDate.now();

        VideoDaw v1 = new VideoDaw(cif, direccion, fechaAlta, new LinkedList<>(), new LinkedList<>());
        BD.registrarVideoclub(v1);

        franquicia.add(v1);

        System.out.println("Videoclub registrado bien!");
    }

    //crear peli o videojuego
    public static void registrarArticulo() {
        if (videoclubs.isEmpty()) {
            System.out.println("Primero necesitas registrar un videoclub");
            return;
        }

        System.out.println("¿Qué quieres registrar: Película (p) o Videojuego (v)?");
        char tipo = validarEntrada("^[pPvV]$").toUpperCase().charAt(0);

        System.out.println("Introduce el código del artículo (P-001 o V-001): ");
        String cod = validarEntrada(tipo == 'P' ? "^P-\\d{3}$" : "^V-\\d{3}$");

        System.out.println("Introduce el título del artículo: ");
        String titulo = sc.nextLine();

        LocalDate fechaAlta = LocalDate.now();

        // primero se registra como Articulo
        Articulo articuloBase = new Articulo(cod, titulo, fechaAlta, null);
        BD.registrarArticulo(articuloBase); // debe insertar en la tabla "articulo"

        //registrar según tipo con género y estado inicial
        if (tipo == 'P') {
            System.out.println("Elige el género de la película:");

            GeneroPeli[] generos = GeneroPeli.values();

            System.out.println("Elige el género de la película:");
            for (int i = 0; i < generos.length; i++) {
                System.out.println((i + 1) + ". " + generos[i].name());
            }

            int opcion = Integer.parseInt(validarEntrada("^[1 " + generos.length + "]$"));
            GeneroPeli genero = generos[opcion - 1];

            Pelicula pelicula = new Pelicula(cod, genero);
            BD.registrarPelicula(pelicula); // inserta en la tabla "pelicula"

        } else if (tipo == 'V') {
            System.out.println("Elige el género del videojuego:");

            GeneroJuego[] generos = GeneroJuego.values();

            System.out.println("Elige el género de la película:");
            for (int i = 0; i < generos.length; i++) {
                System.out.println((i + 1) + ". " + generos[i].name());
            }

            int opcion = Integer.parseInt(validarEntrada("^[1-" + generos.length + "]$"));
            GeneroJuego genero = generos[opcion - 1];


            Videojuego videojuego = new Videojuego(cod, genero);
            BD.registrarVideojuego(videojuego); // inserta en la tabla "videojuego"
        }

        System.out.println("Artículo registrado correctamente.");
    }


    //crear peli o videojuego ()
    public static void registrarCliente() {

        if (videoclubs.isEmpty()) {
            System.out.println("Primero necesitas registrar un videoclub");
        }

        System.out.println("Introduce el dni del cliente (Ej: 12345678A): ");
        String dni = validarEntrada("^\\d{8}[A-Z]$");

        System.out.println("Introduce el nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.println("Introduce la direccion del cliente: ");
        String direccion = sc.nextLine();

        System.out.println("Introduce su fecha de nacimiento(yyyy-mm-dd): ");
        LocalDate fechaNacimiento = LocalDate.parse(sc.nextLine());

        System.out.println("Introduce el numero de socio (S-001): ");
        String numSocio = validarEntrada("^S-\\d{3}$");

        Cliente c1 = new Cliente(null, numSocio, direccion, dni, fechaNacimiento, nombre);
        BD.registrarCliente(c1);


        System.out.println("Cliente registrado bien!");
    }












//------------------------------------------------------------
//METODOS PARA ALQUILAR, DEVOLVER Y DAR DE BAJA CLIENTES Y ARTICULOS














    public static void alquilarArticulo() {

            monstrarArticulos();

            System.out.println("Introduce el codigo del artículo:");
            String cod = sc.nextLine();

            System.out.println("Es una pelicula (P) o un videojuego (V)?");
            String tipo = sc.nextLine().toUpperCase();

            System.out.println("Introduce el DNI del cliente:");
            String dni = sc.nextLine();

            boolean esPelicula = tipo.equals("P");

            int result = BD.alquilar(dni, cod, esPelicula);

            if (result > 0) {
                for (Articulo a : articulos) {
                    if (a.getCod().equalsIgnoreCase(cod)) {
                        if (esPelicula && a instanceof Pelicula) {
                            ((Pelicula) a).setIsAlquilada(true);
                        } else if (!esPelicula && a instanceof Videojuego) {
                            ((Videojuego) a).setIsAlquilada(true);
                        }
                        break;
                    }
                }

                System.out.println("Artículo alquilado!");
            } else {
                System.out.println("Error al alquilar el articulo.");
            }


    }


    public static void devolverArticulo() throws TiempoExcendidoEx {
        monstrarArticulos();

        System.out.println("Introduce el codigo del articulo:");
        String cod = sc.nextLine();

        System.out.println("Es una pelicula (P) o un videojuego (V)?");
        String tipo = sc.nextLine().toUpperCase();

        System.out.println("Introduce el DNI del cliente:");
        String dni = sc.nextLine();

        boolean esPelicula = tipo.equals("P");

        int result = BD.devolver(dni, cod, esPelicula);

        if (result > 0) {
            for (Articulo a : articulos) {
                if (a.getCod().equalsIgnoreCase(cod)) {
                    if (esPelicula && a instanceof Pelicula) {
                        ((Pelicula) a).setIsAlquilada(true);
                    } else if (!esPelicula && a instanceof Videojuego) {
                        ((Videojuego) a).setIsAlquilada(true);
                    }
                    break;
                }
            }

            System.out.println("Articulo devuelto!");
        } else {
            System.out.println("Error al devolver el articulo.");
        }

    }















    public static void darBajaArticulo() {
        monstrarArticulos();

        System.out.println("Introduce el codigo del articulo que quieres dar de baja:");
        String cod = sc.nextLine();

        BD.darDeBajaArticulo(cod);

    }

    public static void darBajaCliente() {

        monstrarClientes();

        System.out.println("Introduce el dni del cliente que quieres dar de baja:");
        String dni = sc.nextLine();

        BD.darDeBajaCliente(dni);

    }





















//------------------------------------------------------------
//VALIDAR LOS DATOS INTRODUCIDOS Y MONSTRAR LOS ARTICULOS








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




    public static void monstrarArticulos() {
        List<Articulo> articulos = BD.getArticulos();
        for (Articulo articulo : articulos) {
            System.out.println(articulo.toString());
        }

        if (articulos.isEmpty()) {
            System.out.println("No hay articulos en el Inventario");
        }
    }

    public static void monstrarClientes() {
        List<Cliente> clientes = BD.getClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }

        if (clientes.isEmpty()) {
            System.out.println("No hay articulos en el Inventario");
        }
    }






}
