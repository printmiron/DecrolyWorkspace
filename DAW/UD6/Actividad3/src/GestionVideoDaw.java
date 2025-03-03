
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GestionVideoDaw {

     private static final Scanner sc = new Scanner(System.in);

    private static List<VideoDaw> videoclubs = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Articulo> articulos = new ArrayList<>();

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws Exception {
        //carga los datos al principio al iniciar la programa
        cargarDatos();
        Scanner sc = new Scanner(System.in);

        
        System.out.println("Videoclubs cargados: " + videoclubs.size());
        System.out.println("Clientes cargados: " + clientes.size());
        System.out.println("Articulos cargados: " + articulos.size());
        System.out.println("Videoclubs cargados: " + videoclubs.size());







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
                    registrarVideoClub(sc, videoclubs);
                    break;

                case "2":
                    registrarArticulo(sc);
                    break;

                case "3":
                    registrarCliente(sc);
                    break;

                case "4":
                    alquilarArticulo(sc);
                    break;

                case "5":
                    devolverArticulo(sc);
                    break;

                case "6":
                    darBajaCliente(sc);
                    break;

                case "7":
                    darBajaArticulo(sc);
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
    public static void registrarVideoClub(Scanner sc, List<VideoDaw> franquicia) {

        System.out.println("Introduce el CIF del Videoclub (Ej: A12345678): ");
        String cif = validarEntrada("[A-HJNP-SUVW][0-9]{8}");

        System.out.println("Introduce la direccion del Vieoclub: ");
        String direccion = sc.nextLine();

        LocalDate fechaAlta = LocalDate.now();

        VideoDaw v1 = new VideoDaw(cif, direccion, fechaAlta, new LinkedList<>(), new LinkedList<>());
        videoclubs.add(v1);
        guardarVideoclub();

        System.out.println("Videoclub registrado bien!");
    }

    //crear peli o videojuego ()
    public static void registrarArticulo(Scanner sc) {

        if (videoclubs.isEmpty()) {
            System.out.println("Primero necesitas registrar un videoclub");
        }

        System.out.println("Que quieres registar pelicula o videojuego (p/v)?");
        char tipo = validarEntrada("^[pPvV]$").toUpperCase().charAt(0);

        System.out.println("Introduce el codigo del articulo (P-001 o V-001): ");
        String cod = validarEntrada(tipo == 'P' ? "^P-\\d{3}$" : "^V-\\d{3}$");

        System.out.println("Introduce el titulo del articulo: ");
        String titulo = sc.nextLine();

        LocalDate fechaAlta = LocalDate.now();

        Articulo a1 = new Articulo(cod, titulo, fechaAlta, null);
        articulos.add(a1);
        guardarArticulos();

        System.out.println("Pelicula o Videojuego registrado bien!");
    }

    //crear peli o videojuego ()
    public static void registrarCliente(Scanner sc) {

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
        clientes.add(c1);
        guardarClientes();

        System.out.println("Cliente registrado bien!");
    }














//------------------------------------------------------------
//METODOS PARA ALQUILAR, DEVOLVER Y DAR DE BAJA CLIENTES Y ARTICULOS









    public static void alquilarArticulo(Scanner sc) {

        if (videoclubs.isEmpty()) {
            System.out.println("Primero necesitas registrar un videoclub");
        }

        //devulve la lista de todos los articulos con sus codigos y tipo
        for (Articulo articulos : articulos) {
            System.out.println(articulos.toString());
        }

        System.out.println("Introduce el codigo del articulo que quieres alquilar");
        String codigoAlquiler = sc.nextLine();
        System.out.println("Introduce el DNI del cliente:");
        String dniAlquiler = sc.nextLine();

        //buscar articulo y si es una peli o videojueo
        Pelicula peliculaAlquilar = null;
        Videojuego videojuegoAlquilar = null;

        for (Articulo articulos : articulos) {
            if (articulos.getCod().equalsIgnoreCase(codigoAlquiler)) {
                if (articulos instanceof Pelicula) {
                    peliculaAlquilar = (Pelicula) articulos;
                } else if (articulos instanceof Videojuego) {
                    videojuegoAlquilar = (Videojuego) articulos;
                }
                break;
            }
        }

        if (peliculaAlquilar == null || videojuegoAlquilar == null) {
            System.out.println("Pelicula o Videojuego no encontrado!");
        }

        //buscar cliente por dni
        Cliente clienteAquilier = null;

        for (Cliente clientes : clientes) {
            if (clientes.getDni().equalsIgnoreCase(dniAlquiler)) {
                clienteAquilier = clientes;
                break;
            }
        }

        if (clienteAquilier == null) {
            System.out.println("Cliente no encontrado!");
        }

        //alquilar si se encuaentra el articulo y el cliente
        boolean alquilado = false;
        if (peliculaAlquilar != null) {
            //qui tenia el error porque los metodos (alquilarPelicula/alquilarJuego desde VideoDaw) necesitan un objeto especifico para llamarles,
            //por eso como tenemos una lista de VideoClubs tenemos que elegir uno en concreto, por eso hago ".get(index:0)" coge simpre el primer videoclub

            alquilado = videoclubs.get(0).alquilarPelicula(peliculaAlquilar, clienteAquilier);
        } else if (videojuegoAlquilar != null) {
            alquilado = videoclubs.get(0).alquilarJuego(videojuegoAlquilar, clienteAquilier);
        }

        if (alquilado) {
            System.out.println("Articulo alquilado bien!");
        } else {
            System.out.println("Error al alquilar articulo!");
        }
    }

    public static void devolverArticulo(Scanner sc) throws TiempoExcendidoEx {
        if (videoclubs.isEmpty()) {
            System.out.println("Primero necesitas registrar un videoclub");
        }

        //devulve la lista de todos los articulos con sus parametros
        for (Articulo articulos : articulos) {
            System.out.println(articulos.toString());
        }

        System.out.println("Introduce el codigo del articulo que quieres devolver");
        String codigoDevolver = sc.nextLine();
        System.out.println("Introduce el DNI del cliente:");
        String dniDevolver = sc.nextLine();

        //buscar articulo y si es una peli o videojueo
        Pelicula peliculaDeolver = null;
        Videojuego videojuegoDevolver = null;

        for (Articulo articulos : articulos) {
            if (articulos.getCod().equalsIgnoreCase(codigoDevolver)) {
                if (articulos instanceof Pelicula) {
                    peliculaDeolver = (Pelicula) articulos;
                } else if (articulos instanceof Videojuego) {
                    videojuegoDevolver = (Videojuego) articulos;
                }
                break;
            }
        }

        if (peliculaDeolver == null || videojuegoDevolver == null) {
            System.out.println("Pelicula o Videojuego no encontrado!");
        }

        //buscar cliente por dni
        Cliente clienteDevolver = null;

        for (Cliente clientes : clientes) {
            if (clientes.getDni().equalsIgnoreCase(dniDevolver)) {
                clienteDevolver = clientes;
                break;
            }
        }

        if (clienteDevolver == null) {
            System.out.println("Cliente no encontrado!");
        }

        //devolver si se encuaentra el articulo y el cliente
        boolean devuelta = false;
        try {
            if (peliculaDeolver != null) {
                devuelta = videoclubs.get(0).devolverPelicula(peliculaDeolver, clienteDevolver);
            } else if (videojuegoDevolver != null) {
                devuelta = videoclubs.get(0).devolverJuego(videojuegoDevolver, clienteDevolver);
            }

            if (devuelta) {
                System.out.println("Articulo devuelto bien!");
            } else {
                System.out.println("Error al devolver articulo!");
            }
        } catch (TiempoExcendidoEx e) {
            System.out.println("Aviso! El tiempo de alquiler ha sido excedido. No se puede devolver el articulo!");
        }

    }

    public static void darBajaArticulo(Scanner sc) {
        if (videoclubs.isEmpty()) {
            System.out.println("Primero necesitas registrar un videoclub");
        }

        //devulve la lista de todos los articulos con sus parametros
        for (Articulo articulos : articulos) {
            System.out.println(articulos.toString());
        }

        System.out.println("Introduce el codigo del articulo que quieres dar de baja:");
        String codBaja = sc.nextLine();

        // buscar articulo por codigo
        Articulo articuloBaja = null;
        for (Articulo articulos : articulos) {
            if (articulos != null && articulos.getCod().equalsIgnoreCase(codBaja)) {
                articuloBaja = articulos;
                break;
            }
        }

        if (codBaja == null) {
            System.out.println("Articulo no encontrado!");
            return;
        }

        if (articuloBaja != null) {
            boolean baja = videoclubs.get(0).darBajaArticulo(articuloBaja);
            if (baja) {
                System.out.println("Articulo dado de baja correctamente");
            } else {
                System.out.println("No se pudo dar de baja al articulo");
            }
        } else {
            System.out.println("Articulo no encontrado");
        }

    }

    public static void darBajaCliente(Scanner sc) {

        if (videoclubs.isEmpty()) {
            System.out.println("Primero necesitas registrar un videoclub");
        }

        for (Cliente clientes : clientes) {
            System.out.println(clientes.toString());
            
        }

        System.out.println("Introduce el DNI del cliente que quieres dar de baja:");
        String dniBaja = sc.nextLine();

        // buscar cliente por dni
        Cliente clienteBaja = null;
        for (Cliente clientes : videoclubs.get(0).getClientesRegistrados()) {
            if (clientes != null && clientes.getDni().equalsIgnoreCase(dniBaja)) {
                clienteBaja = clientes;
                break;
            }
        }
        if (dniBaja == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        //dar de baja al cliente
        if (clienteBaja != null) {
            boolean baja = videoclubs.get(0).darBajaCliente(clienteBaja);
            if (baja) {
                System.out.println("Cliente dado de baja correctamente");
            } else {
                System.out.println("No se pudo dar de baja al cliente");
            }
        } else {
            System.out.println("Cliente no encontrado");
        }

    }









//------------------------------------------------------------
//GUARDAR - CARGAR FIHCEROS
    //guardar datos en los ficheros, no me vale las rutas relativas por eso utilizo las absolutas, para copiar hago click derecho sobre 
    //Fichero.dat y "Copy Path" y lo escribo en la linea correspondiente










    public static void guardarDatos() {
        guardarVideoclub();
        guardarClientes();
        guardarArticulos();

    }

    private static void guardarVideoclub() {
        try (FileWriter file = new FileWriter("C:\\Users\\cloud\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad3\\resources\\Videoclubs.dat"); BufferedWriter writer = new BufferedWriter(file);) {
            for (VideoDaw v : videoclubs) {
                writer.write(v.getCif() + "," + v.getDireccion() + "," + v.getFechaAlta());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar videoclub" + e.getMessage());
        }

    }

    private static void guardarClientes() {
        try (FileWriter file = new FileWriter("C:\\Users\\cloud\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad3\\resources\\Clientes.dat"); BufferedWriter writer = new BufferedWriter(file);) {
            for (Cliente c : clientes) {
                writer.write(c.getFechaBaja() + "," + c.getNumSocio() + "," + c.getDireccion() + "," + c.getDni() + "," + c.getFechaNacimiento() + "," + c.getNombre());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar cliente" + e.getMessage());
        }
    }

    private static void guardarArticulos() {
        try (FileWriter file = new FileWriter("C:\\Users\\cloud\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad3\\resources\\Articulos.dat"); BufferedWriter writer = new BufferedWriter(file);) {
            for (Articulo a : articulos) {
                writer.write(a.getCod() + "," + a.getTitulo() + "," + a.getFechaRegistro() + "," + a.getFechaBaja());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar articulo" + e.getMessage());
        }
    }

    //cargar datos desde ficheros
    public static void cargarDatos() {
        cargarVideoclub();
        cargarClientes();
        cargarArticulos();

    }

    private static void cargarVideoclub() {
        try (FileReader file = new FileReader("C:\\Users\\cloud\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad3\\resources\\Videoclubs.dat"); BufferedReader reader = new BufferedReader(file);) {
            String linea = reader.readLine();
            while (linea != null) {

                String[] datos = linea.split(",");

                videoclubs.add(new VideoDaw(datos[0], datos[1], LocalDate.parse(datos[2]), new LinkedList<>(), new LinkedList<>()));

                linea = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error al cargar videoclubs " + e.getMessage());
        }
    }

    private static void cargarClientes() {
        try (FileReader file = new FileReader("C:\\Users\\cloud\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad3\\resources\\Clientes.dat"); BufferedReader reader = new BufferedReader(file);) {
            String linea = reader.readLine();
            while (linea != null) {

                String[] datos = linea.split(",");


                LocalDate fechaBaja = datos[0].equals("null") ? null : LocalDate.parse(datos[0]);
                clientes.add(new Cliente(fechaBaja, datos[1], datos[2], datos[3], LocalDate.parse(datos[4]), datos[5]));

                linea = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error al cargar clientes " + e.getMessage());
        }
    }

    private static void cargarArticulos() {
        try (FileReader file = new FileReader("C:\\Users\\cloud\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad3\\resources\\Articulos.dat"); BufferedReader reader = new BufferedReader(file);) {
            String linea = reader.readLine();
            while (linea != null) {

                String[] datos = linea.split(",");


                // Aseguramos que hay al menos 4 elementos antes de acceder a datos[3]
                LocalDate fechaAlta = datos.length > 2 ? LocalDate.parse(datos[2]) : null;
                LocalDate fechaBaja = (datos.length > 3 && !datos[3].equals("null")) ? LocalDate.parse(datos[3]) : null;

                articulos.add(new Articulo(datos[0], datos[1], fechaAlta, fechaBaja));

                linea = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error al cargar videoclubs " + e.getMessage());
        }
    }

//------------------------------------------------------------
//VALIDAR LOS DATOS INTRODUCIDOS

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
