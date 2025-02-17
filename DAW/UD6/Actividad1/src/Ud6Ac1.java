
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ud6Ac1 {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        List<Producto> productos = new LinkedList<>();

        String opcion;

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
                    String coidgo = sc.nextLine();

                    System.out.println("Introduce el NOMBRE del producto: ");
                    String nombre = sc.nextLine();

                    System.out.println("Introduce CANTIDAD del producto: ");
                    int cantidad = sc.nextInt();

                    System.out.println("Introduce PRECIO del producto: ");
                    double precio = sc.nextDouble();

                    Producto p1 = new Producto(coidgo, nombre, cantidad, precio);

                    productos.add(p1);

                    System.out.println("Producto registrado!");

                    break;

                case "2":

                    System.out.println("1. Mostrar productos registrados ahora ");
                    System.out.println("2. Mostrar productos desde almacen ");
                    opcion = sc.nextLine();

                    switch (opcion) {
                        case "1":

                            if (productos.isEmpty()) {
                                System.out.println("No hay productos.");
                            } else {
                                for (Producto producto : productos) {
                                    System.out.println(producto.toString());
                                }
                            }

                            break;

                        case "2":

                            //Lectura de fichero Almacen.dat
                            try (FileReader file = new FileReader("./resources/Almacen.dat"); BufferedReader reader = new BufferedReader(file);) {
                                String linea = reader.readLine();
                                while (linea != null) {

                                    String[] datos = linea.split(",");

                                    Producto p2 = new Producto(datos[0], (datos[1]), Integer.parseInt(datos[2]), Double.parseDouble(datos[3]));

                                    productos.add(p2);

                                    linea = reader.readLine();
                                }

                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }

                            break;
                        default:
                    }
                    break;

                case "3":

                    System.out.println("Introduce el codigo del producto que quieres eliminar: ");
                    String codigoEliminar = sc.nextLine();

                    for (Producto producto : productos) {
                        if (producto.getCodigo().equalsIgnoreCase(codigoEliminar)) {
                            productos.remove(producto);
                            System.out.println("Producto con codigo ( " + producto.getCodigo() + ") ha sido eliminado");
                        }
                    }

                    break;

                //case 4: es para guardar todos los productos que has registrado en case 1(LinkedList), guarda todo en Almacen.dat
                case "4":

                    try (FileWriter file = new FileWriter("./resources/Almacen.dat", false); BufferedWriter writer = new BufferedWriter(file);) {

                        writer.write("");
                        writer.newLine();

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "5":
                    System.out.println("Hasta Luego");
                    break;

                default:
                    System.out.println("Opcion no valido!");

            }

        } while (!"5".equals(opcion));

    }
}
