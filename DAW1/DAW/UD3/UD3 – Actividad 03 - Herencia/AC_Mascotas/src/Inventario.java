
import java.time.LocalDate;
import java.util.Scanner;
/**
 * Clase que gestiona el inverntario de mascotas
 * Permite varios acciones como insertar, borrar etc.
 * 
 * @author Pavel Miron
 * @version 1.0.1
 * @since 19-02-2025
 *
 */
public class Inventario {
	
    private Mascotas[] mascotas;
    private int indiceActual; // numero de animales actuales

    /**
     *Constructor del inventario
     * 
     * @param capacidad Capacidad maxima del inventario
     */
    
    public Inventario(int capacidad) {
        mascotas = new Mascotas[capacidad];
        indiceActual = 0;
    }

    /**
     * Metodo para limpiar el buffer
     * 
     * @param scanner
     */
    //con esto tenia muchas errores hasta que encontro porque se tiene que poner esto
    private void limpiarBuffer(Scanner scanner) {
        scanner.nextLine();
    }

    /**
     * Insertar una nueva mascota
     * 
     * @param scanner Para coger los datos de la mascota
     */
    
    // Insertar una mascota
    public void insertarMascota(Scanner scanner) {
        if (indiceActual >= mascotas.length) {
            System.out.println("Inventario lleno no se puede agregar mas mascotas");
            return;
        }

        System.out.print("Ingrese el tipo de mascota (Perro, Gato, Loro, Canario): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la edad: ");
        int edad = scanner.nextInt();
        limpiarBuffer(scanner);

        System.out.print("Ingrese el estado (true para saludable, false para enfermo): ");
        boolean estado = scanner.nextBoolean();
        limpiarBuffer(scanner);

        System.out.print("Anio de nacimiento: ");
        int anio = scanner.nextInt();
        //si no es entre 1 y 12 da error
        System.out.print("Mes de nacimiento (1-12): ");
        int mes = scanner.nextInt();

        System.out.print("Dia de nacimiento: ");
        int dia = scanner.nextInt();
        limpiarBuffer(scanner);

        LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
        Mascotas mascota = null;

        switch (tipo) {
            case "Perro":
                System.out.print("Ingrese la raza: ");
                String raza = scanner.nextLine();

                System.out.print("Tiene pulgas? (true/false): ");
                boolean pulgas = scanner.nextBoolean();
                limpiarBuffer(scanner);

                mascota = new Perro(nombre, edad, estado, fechaNacimiento, raza, pulgas);
                break;

            case "Gato":
                System.out.print("Ingrese el color: ");
                String colorGato = scanner.nextLine();

                System.out.print("Tiene pelo largo? (true/false): ");
                boolean peloLargo = scanner.nextBoolean();
                limpiarBuffer(scanner);

                mascota = new Gato(nombre, edad, estado, fechaNacimiento, colorGato, peloLargo);
                break;

            case "Loro":
                System.out.print("Ingrese el tipo de pico: ");
                String picoLoro = scanner.nextLine();

                System.out.print("Puede volar? (true/false): ");
                boolean vuelaLoro = scanner.nextBoolean();
                limpiarBuffer(scanner);

                System.out.print("Ingrese el origen: ");
                String origen = scanner.nextLine();

                System.out.print("Puede hablar? (true/false): ");
                boolean habla = scanner.nextBoolean();
                limpiarBuffer(scanner);

                mascota = new Loro(nombre, edad, estado, fechaNacimiento, picoLoro, vuelaLoro, origen, habla);
                break;

            case "Canario":
                System.out.print("Ingrese el tipo de pico: ");
                String picoCanario = scanner.nextLine();

                System.out.print("Puede volar? (true/false): ");
                boolean vuelaCanario = scanner.nextBoolean();
                limpiarBuffer(scanner);

                System.out.print("Ingrese el color: ");
                String colorCanario = scanner.nextLine();

                System.out.print("Canta? (true/false): ");
                boolean canta = scanner.nextBoolean();
                limpiarBuffer(scanner);

                mascota = new Canario(nombre, edad, estado, fechaNacimiento, picoCanario, vuelaCanario, colorCanario, canta);
                break;

            default:
                System.out.println("Tipo de mascota no acceptable.");
                return;
        }

        mascotas[indiceActual++] = mascota;
        System.out.println("Mascota agregada.");
    }
    
    /**
     * Elimina una mascota del inventario por su nombre.
     * 
     * @param scanner Para capturar el nombre de la mascota a eliminar.
     */

    // Eliminar una mascota
    public void eliminarMascota(Scanner scanner) {
        System.out.print("Ingrese el nombre de la mascota a eliminar: ");
        String nombre = scanner.nextLine();

        for (int i = 0; i < indiceActual; i++) {
            if (mascotas[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Eliminando: " + mascotas[i].getNombre());
                mascotas[i] = mascotas[indiceActual - 1];
                mascotas[indiceActual - 1] = null;
                indiceActual--;
                return;
            }
        }
        System.out.println("No se encontro mascota con nombre: " + nombre);
    }
    
    /**
     * Vacia completamente el inventario
     */
    
    // Vaciar el inventario
    public void vaciarInventario() {
        for (int i = 0; i < indiceActual; i++) {
            mascotas[i] = null;
        }
        indiceActual = 0;
        System.out.println("Todos las mascotas fueron eliminadas.");
    }
    
    /**
     * Muestra la lista de animales en el inventario
     */
    
    // Mostrar lista de animales
    public void mostrarListaAnimales() {
        for (int i = 0; i < indiceActual; i++) {
            System.out.println(mascotas[i].getTipo() + " - " + mascotas[i].getNombre());
        }
    }

    
    // Mostrar todos los datos de un animal
    public void mostrarDatosAnimal(Scanner scanner) {
        System.out.print("Ingrese el nombre de la mascota: ");
        String nombre = scanner.nextLine();

        for (int i = 0; i < indiceActual; i++) {
            if (mascotas[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(mascotas[i].muestraMascota());
                return;
            }
        }
        System.out.println("No se encontro mascota con nombre: " + nombre);
    }

    // Mostrar todos los datos de todas las mascotas
    public void mostrarTodosLosDatos() {
        System.out.println("Datos de todas las mascotas:");
        for (int i = 0; i < indiceActual; i++) {
            System.out.println(mascotas[i].muestraMascota());
        }
    }

    /**
     * Metodo principal que ejecuta el programa de inventario de mascotas
     * 
     * @param args 
     */
    
    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario(40);
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Mostrar la lista de animales");
            System.out.println("2. Mostrar todos los datos de un animal concreto");
            System.out.println("3. Mostrar todos los datos de todos los animales");
            System.out.println("4. Insertar animales en el inventario");
            System.out.println("5. Eliminar animales del inventario.");
            System.out.println("6. Vaciar el inventario");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    inventario.mostrarListaAnimales();
                    break;

                case 2:
                    inventario.mostrarDatosAnimal(scanner);
                    break;

                case 3:
                    inventario.mostrarTodosLosDatos();
                    break;

                case 4:
                    inventario.insertarMascota(scanner);
                    break;

                case 5:
                    inventario.eliminarMascota(scanner);
                    break;

                case 6:
                    inventario.vaciarInventario();
                    break;

                case 7:
                    salir = true;
                    break;

                default:
                    System.out.println("Opcion no es valida");
            }
        }

        scanner.close();
    }
}
