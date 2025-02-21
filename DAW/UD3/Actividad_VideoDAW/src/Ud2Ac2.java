
//En Alquilaer pelicula o devolver, dar de baja cliente y pelicula si los datos no son validos no sale nada si son
//validos ta sale el mesaje lo se que tienen que recorrer un for para comprobar si hay peliculas
import java.util.Scanner;

public class Ud2Ac2 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        VideoDAW videoClub = null;

        String opcion = null;
        do {
            System.out.println("1. Crear y registar VideoClub en la franquicia");
            System.out.println("2. Registar pelicula en videoclub");
            System.out.println("3. Crear y registar cliente en videoclub");
            System.out.println("4. Alquilar pelicula"); //
            System.out.println("5. Devolver pelicula"); //
            System.out.println("6. Dar de baja cliente");
            System.out.println("7. Dar de baja pelicula");//
            System.out.println("8. Ver todos los clientes");
            System.out.println("9. Ver todas las peliculas");
            System.out.println("10. Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                //Crear y registrar VideoClub
                case "1":
                    final String patronCIF = "[A-Z][0-9]{8}";
                    String cif = Utils.comprobarPatronRepetidamente(patronCIF, "Introduce su CIF: Ejemplo (A12345678)");

                    System.out.println("Y su direccion: ");
                    String direccionCV = sc.nextLine();

                    videoClub = new VideoDAW(cif, direccionCV);
                    break;

                //Registar pelicula en videoclub
                case "2":
                    if (videoClub == null) {
                        System.out.println("Primero necesitas registrar un videoclub");
                        break;
                    }

                    System.out.println("Introduce el titulo de la pelicula: ");
                    String titulo = sc.nextLine();

                    System.out.println("Introduce su genero: (Ejemplos: Accion, Aventura, Catastrofe, Ciencia, Ficcion, Comedia, Documentales, Drama, Fantasia)");
                    String genero = sc.nextLine();

                    Pelicula regPelicula = new Pelicula(0, titulo, null);

                    videoClub.registrarPelicula(regPelicula);
                    System.out.println("Pelicula registrada correctamente.");
                    break;

                //Crear y registar cliente en videoclub
                case "3":
                    if (videoClub == null) {
                        System.out.println("Primero necesitas registrar un videoclub");
                        break;
                    }

                    final String patronDNI = "[A-Z][0-9]{7}[A-Z]";
                    String dni = Utils.comprobarPatronRepetidamente(patronDNI, "Introduce su DNI: Ejemplo (Y1234567K)");

                    final String patronNombre = "[A-Z][a-z]+ [A-Z][a-z]+"; // Al menos Nombre y Apellido
                    String nombre = Utils.comprobarPatronRepetidamente(patronNombre, "Introduce su Nombre y Apellido: ");

                    System.out.println("Introduce su direccion: ");
                    String direccion = sc.nextLine();

                    Cliente regCliente = new Cliente(dni, nombre, direccion);

                    videoClub.registrarCliente(regCliente);
                    System.out.println("Cliente registrado correctamente");
                    break;

                //Alquilar pelicula
                case "4":
                    if (videoClub == null) {
                        System.out.println("Primero necesitas registrar un videoclub");
                        break;
                    }
                    System.out.println("Introduce el nombre de la pelicula que quieres alquilar");
                    String tituloAlquiler = sc.nextLine();
                    System.out.println("Introduce el DNI del cliente:");
                    String dniAlquiler = sc.nextLine();

                    // Buscar la pelicula por titulo
                    Pelicula peliculaALquilar = null;
                    for (Pelicula p : videoClub.getPeliculas()) {
                        if (p != null && p.getTitulo().equalsIgnoreCase(tituloAlquiler)) {
                            peliculaALquilar = p;
                            break;
                        }
                    }
                    if (peliculaALquilar == null) {
                        System.out.println("Pelicula no encontrada");
                        break;
                    }
                    // Buscar el cliente por DNI
                    Cliente clienteAlquiler = null;
                    for (Cliente c : videoClub.getClientes()) {
                        if (c != null && c.getDni().equalsIgnoreCase(dniAlquiler)) {
                            clienteAlquiler = c;
                            break;
                        }
                    }
                    if (clienteAlquiler == null) {
                        System.out.println("Cliente no encontrado");
                        break;
                    }
                    // Alquilar si se encuentra el cliente y pelicula
                    if (peliculaALquilar != null && clienteAlquiler != null) {
                        boolean alquilada = videoClub.alquilarPelicula(peliculaALquilar, clienteAlquiler);
                        if (alquilada) {
                            System.out.println("Pelicula alquilada correctamente");
                        } else {
                            System.out.println("No se pudo alquilar la pelicula y verifica si esta disponible");
                        }
                    }
                    break;

                //Devolver pelicula
                case "5":
                    if (videoClub == null) {
                        System.out.println("Primero necesitas registrar un videoclub");
                        break;
                    }
                    System.out.println("Introduce el nombre de la pelicula que quieres devolver");
                    String tituloDevolucion = sc.nextLine();
                    System.out.println("Introduce el DNI del cliente:");
                    String dniDevolucion = sc.nextLine();

                    // Buscar la pelicula por titulo
                    Pelicula peliculaADevolver = null;
                    for (Pelicula p : videoClub.getPeliculas()) {
                        if (p != null && p.getTitulo().equalsIgnoreCase(tituloDevolucion)) {
                            peliculaADevolver = p;
                            break;
                        }
                    }
                    if (peliculaADevolver == null) {
                        System.out.println("Pelicula no encontrada");
                        break;
                    }
                    // Buscar el cliente por DNI
                    Cliente clienteDevolucion = null;
                    for (Cliente c : videoClub.getClientes()) {
                        if (c != null && c.getDni().equalsIgnoreCase(dniDevolucion)) {
                            clienteDevolucion = c;
                            break;
                        }
                    }
                    if (clienteDevolucion == null) {
                        System.out.println("Cliente no encontrado");
                        break;
                    }
                    // Devolver si se encuentra el cliente y pelicula
                    if (peliculaADevolver != null && clienteDevolucion != null) {
                        boolean devuelta = videoClub.devolverPelicula(peliculaADevolver, clienteDevolucion);
                        if (devuelta) {
                            System.out.println("Pelicula devuelta correctamente");
                        } else {
                            System.out.println("No se pudo devolver la pelicula y verifica si esta alquilada");
                        }
                    }

                    break;

                //Dar de baja cliente
                case "6":
                    if (videoClub == null) {
                        System.out.println("Primero necesitas registrar un videoclub");
                        break;
                    }
                    System.out.println("Introduce el DNI del cliente que quieres dar de baja:");
                    String dniBaja = sc.nextLine();

                    // Buscar cliente por DNI
                    Cliente clienteBaja = null;
                    for (Cliente c : videoClub.getClientes()) {
                        if (c != null && c.getDni().equalsIgnoreCase(dniBaja)) {
                            clienteBaja = c;
                            break;
                        }
                    }
                    if (dniBaja == null) {
                        System.out.println("Cliente no encontrado");
                        break;
                    }
                    // Dar de baja al cliente
                    if (clienteBaja != null) {
                        boolean baja = videoClub.darBajaCliente(clienteBaja);
                        if (baja) {
                            System.out.println("Cliente dado de baja correctamente");
                        } else {
                            System.out.println("No se pudo dar de baja al cliente");
                        }
                    } else {
                        System.out.println("Cliente no encontrado");
                    }

                    break;

                //Dar de baja pelicula
                case "7":
                    if (videoClub == null) {
                        System.out.println("Primero necesitas registrar un videoclub");
                        break;
                    }

                    System.out.println("Introduce el nombre de la pelicula a dar de baja:");
                    String tituloBaja = sc.nextLine();

                    // Buscar pelicula por titulo
                    Pelicula peliculaBaja = null;
                    for (Pelicula p : videoClub.getPeliculas()) {
                        if (p != null && p.getTitulo().equalsIgnoreCase(tituloBaja)) {
                            peliculaBaja = p;
                            break;
                        }
                    }

                    // Dar de baja la pelicula
                    if (peliculaBaja != null) {
                        boolean bajaPelicula = videoClub.darBajaPelicula(peliculaBaja);
                        if (bajaPelicula) {
                            System.out.println("Pelicula dada de baja correctamente");
                        } else {
                            System.out.println("No se pudo dar de baja la pelicula");
                        }
                    } else {
                        System.out.println("Pelicula no encontrada");
                    }

                    break;

                case "8":
                if (videoClub == null) {
                    System.out.println("Primero necesitas registrar un videoclub");
                    break;
                }

                Cliente[] clientes = videoClub.getClientes();
                System.out.println("Clientes registrados en el videoclub:");
                boolean hayClientes = false;
            
                for (Cliente cliente : clientes) {
                    if (cliente != null) {
                        hayClientes = true;
                        System.out.println(cliente.mostrarInfoCliente());
                        System.out.println(cliente.mostrarPeliculasAlquiladas());
                        System.out.println(" ");
                    }
                }
            
                if (!hayClientes) {
                    System.out.println("No hay clientes registrados.");
                }
                break;
                   

                case "9":
                if (videoClub == null) {
                    System.out.println("Primero necesitas registrar un videoclub");
                    break;
                }

                Pelicula[] peliculas = videoClub.getPeliculas();
                System.out.println("Peliculas registradas en el videoclub:");
                boolean hayPeliculas = false;
            
                for (Pelicula pelicula : peliculas) {
                    if (pelicula != null) {
                        hayPeliculas = true;
                        System.out.println(pelicula.mostrarInfoPelicula());
                    }
                }
            
                if (!hayPeliculas) {
                    System.out.println("No hay peliculas registradas");
                }
                    break;

                case "10":
                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opcion no valida, intenta de nuevo.");
            }
        } while (!"10".equals(opcion));

        sc.close();

    }
}
