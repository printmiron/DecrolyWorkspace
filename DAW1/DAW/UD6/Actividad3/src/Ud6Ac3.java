
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ud6Ac3 {

    private static final Scanner sc = new Scanner(System.in);

    private static List<Libro> libros = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        cargarLibros();

        String opcion = null;
        do {
            System.out.println("1) Crear Libro y registrarlo en la Biblioteca (ISBN unico)");
            System.out.println("2) Mostrar Libros existentes por (ISBN, titulo, Autor, Fecha)");
            System.out.println("3) Eliminar Libro por ISBN");
            System.out.println("4) Guardar Libros en el fichero.");
            System.out.println("5) Guardar y Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    crearLibro();
                    break;

                case "2":
                    monstrarLibro();
                    break;

                case "3":
                    eliminarLibro();
                    break;

                case "4":
                    guardarLibros();
                    break;

                case "5":
                    guardarLibros();
                    System.out.println("Hasta luego!");

                    break;
                default:
                    System.out.println("Opcion no valido!");
            }

        } while (!"5".equals(opcion));

    }

// -------------------------
// CREAR LIBRO
    public static void crearLibro() {
        System.out.println("Introduce el ISBN del libro: ");
        String isbn = sc.nextLine();

        //verificamos si el isbn ya existe
        for (Libro li : libros) {
            if (li.getIsbn().equals(isbn)) {
                System.out.println("El isbn ya existe");
                return;
            }
        }

        System.out.println("Introduce el titulo: ");
        String titulo = sc.nextLine();

        System.out.println("Introduce el autor del libro: ");
        String autor = sc.nextLine();

        System.out.println("Introduce la fecha de publicacion: ");
        LocalDate fechaPublicacion = LocalDate.parse(sc.nextLine());

        Libro l1 = new Libro(isbn, titulo, autor, fechaPublicacion);
        libros.add(l1);

    }

// -------------------------
// MONSTRAR LIBRO
    public static void monstrarLibro() {

        if (libros.isEmpty()) {
            System.out.println("No hay libros!");
        }

        for (Libro libros : libros) {
            System.out.println(libros.toString());
        }
    }

// -------------------------
// ELIMINAR LIBRO
    public static void eliminarLibro() {
        System.out.println("Introduce el ISBN del libro que quieres eliminar: ");
        String isbnEliminar = sc.nextLine();

        Libro eliminarLibro = null;
        for (Libro li : libros) {
            if (li.getIsbn().equals(isbnEliminar)) {
                eliminarLibro = li;
            }
        }

        if (eliminarLibro != null) {
            libros.remove(eliminarLibro);
            System.out.println("Libro eliminado!");
        }else{
            System.out.println("Libro no encontrado");
        }

    }

// -------------------------
// GUARDAR LIBROS EN FICHERO
    public static void guardarLibros() {
        try (FileOutputStream file = new FileOutputStream("src\\resources\\Biblioteca.dat", false); ObjectOutputStream writer = new ObjectOutputStream(file)) {

            writer.writeObject(libros);

        } catch (IOException e) {
            System.out.println("Se ha producido un error al guardar: " + e.getMessage());
        }
    }

// -------------------------
// CARGAR LIBROS EN FICHERO
    public static void cargarLibros() {
        boolean eof = false;
        try (FileInputStream file = new FileInputStream("src\\resources\\Biblioteca.dat"); ObjectInputStream reader = new ObjectInputStream(file)) {
            while (!eof) {
                
                libros = (List<Libro>) reader.readObject();

            }
        } catch (EOFException e) {
            eof = true;
            System.out.println("Se ha leido el fichero completo");
        } catch (IOException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }

        for (Libro li : libros) {
            System.out.println(li);
        }
    }



}
