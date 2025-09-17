
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ud6Ac2 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        List<Producto2> productos = new LinkedList<>();
        List<Producto2> productosAlmacen = new LinkedList<>();

        String opcion;
        String opcionAd;
        do {

            System.out.println("1. Crear producto");
            System.out.println("2. Mostrar productos existentes ");
            System.out.println("3. Eliminar producto por codigo");
            System.out.println("4. Guardar productos en el fichero");
            System.out.println("5. Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Introduce CODIGO del producto: ");
                    String codigo = sc.nextLine();

                    System.out.println("Introduce el NOMBRE del producto: ");
                    String nombre = sc.nextLine();

                    System.out.println("Introduce CANTIDAD del producto: ");
                    int cantidad = sc.nextInt();

                    System.out.println("Introduce PRECIO del producto: ");
                    double precio = sc.nextDouble();

                    Producto2 p1 = new Producto2(codigo, nombre, cantidad, precio);

                    productos.add(p1);

                    System.out.println("Producto registrado!");

                    break;

                case "2":
                    //La idea es que los productos registrados "ahora" son los productos creados despues de arrancar la programa, si se reinicia la programa los productos registrados en LinkedList (case1) de borran, pero que se guardan en Alamacen.dat no, y si elegimos mostrar productos de almacen nos enseña solo los productos de almacen.

                    System.out.println("1. Mostrar productos registrados ahora ");
                    System.out.println("2. Mostrar productos desde almacen ");
                    opcionAd = sc.nextLine();

                    switch (opcionAd) {
                        case "1":

                            if (productos.isEmpty()) {
                                System.out.println("No hay productos.");
                            } else {
                                for (Producto2 producto : productos) {
                                    System.out.println(producto.toString());
                                }
                            }

                            break;

                        case "2":

                            productosAlmacen.clear();
                            boolean eof = false;
                            //Lectura de fichero binario Almacen.dat
                            try (FileInputStream file = new FileInputStream("C:\\Users\\daw1\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad2\\resources\\Almacen.dat"); DataInputStream reader = new DataInputStream(file);) {

                                while (!eof) {

                                    String codigo1 = reader.readUTF();
                                    String nombre1 = reader.readUTF();
                                    int cantidad1 = reader.readInt();
                                    double precio1 = reader.readDouble();
                                    Producto2 p2 = new Producto2(codigo1, nombre1, cantidad1, precio1);
                                    productosAlmacen.add(p2);

                                }

                            } catch (EOFException e) {
                                eof = true;
                                
                            } catch (IOException e) {
                                System.out.println(e.getMessage());  
                            }

                            if (productosAlmacen.isEmpty()) {
                                System.out.println("No hay productos en Almacen!");
                            } else {
                                for (Producto2 p2 : productosAlmacen) {
                                    System.out.println(p2);
                                }
                            }
                    }
                    break;

                case "3":

                    System.out.println("Introduce el codigo del producto que quieres eliminar: ");
                    String codigoEliminar = sc.nextLine();

                    for (Producto2 producto : productos) {
                        if (producto.getCodigo().equalsIgnoreCase(codigoEliminar)) {
                            productos.remove(producto);
                            System.out.println("Producto con codigo ( " + producto.getCodigo() + ") ha sido eliminado");
                        } else {
                            System.out.println("No hay producto con codigo: " + codigoEliminar);
                        }
                    }

                    break;

                //case 4: es para guardar todos los productos que has registrado en case 1(LinkedList), guarda todo en Almacen.dat
                case "4":

                    try (FileOutputStream file = new FileOutputStream("C:\\Users\\daw1\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad2\\resources\\Almacen.dat", true); DataOutputStream writer = new DataOutputStream(file);) {

                        for (Producto2 producto : productos) {
                            writer.writeUTF(producto.getCodigo());
                            writer.writeUTF(producto.getNombre());
                            writer.writeInt(producto.getCantidad());
                            writer.writeDouble(producto.getPrecio());
                        }

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Productos guardados en almacen!");

                    break;

                case "5":
                    System.out.println("Hasta Luego!");
                    break;

                default:
                    System.out.println("Opcion no valido!");

            }

        } while (!"5".equals(opcion));

    }
}

//como en ejercicio hay ->
//"Implementa una clase principal con un menú con las siguientes opciones: ... "4. Guardar productos en el fichero" ..., lo entendi que se guardan los productos en programa y despues en Almacen.dat con opcion 4.
