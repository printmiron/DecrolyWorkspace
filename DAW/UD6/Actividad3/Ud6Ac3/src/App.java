
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Libro> li = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        cargarLibros();

        String opcion = null;
        do {
            System.out.println("1) Crear Libro y registrarlo en la Biblioteca (ISBN único)");
            System.out.println("2) Mostrar Libros existentes por (ISBN, titulo, Autor, Fecha)");
            System.out.println("3) Eliminar Libro por ISBN");
            System.out.println("4) Guardar Libros en el fichero.");
            System.out.println("5) Guardar y Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":

                    break;

                case "2":

                    break;

                case "3":

                    break;

                case "4":

                    break;

                case "5":
                    System.out.println("Hasta luego!");

                    break;
                default:
                    System.out.println("Opcion no valido!");
            }

        } while (!"5".equals(opcion));

    }

    public static void guardarLibros() {
        try (FileOutputStream file = new FileOutputStream("src\\resources\\Biblioteca.dat", false); ObjectOutputStream writer = new ObjectOutputStream(file)) {
            for (Libro libros : li) {

                writer.writeUTF(libros.getIsbn());
                writer.writeUTF(libros.getTitulo());
                writer.writeUTF(libros.getAutor());
                writer.writeLong(libros.getFechaPublicacion());   

            }
            

        } catch (IOException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }



    public static void cargarLibros() {
        boolean eof = false;
        try (FileInputStream file = new FileInputStream("src\\resources\\Biblioteca.dat"); ObjectInputStream reader = new ObjectInputStream(file)){
            while(!eof){
                Alumno aLeido = (Alumno) reader.readObject();
                alumnosLeidos.add(aLeido);
            }
        } catch (EOFException e) {
            eof = true;
            System.out.println("Se ha leido el fichero completo");
        } catch (IOException e) {
            System.out.println("Se ha producido un error: "+e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("Se ha producido un error: "+e.getMessage());
        }

        for (Alumno al : alumnosLeidos) {
            System.out.println(al);
        }
    }






}
