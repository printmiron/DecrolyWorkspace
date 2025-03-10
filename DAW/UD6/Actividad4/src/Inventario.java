
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Inventario {

    private static final Scanner sc = new Scanner(System.in);

    private static List<Producto> productos = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        cargarProductos();

        String opcion = null;
        do {
            System.out.println("1) Mostrar Productos en el Inventario");
            System.out.println("2) Eliminar Producto por referencia");
            System.out.println("3) Guardar y Salir");
            System.out.println("4) Guardar Libros en el fichero.");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":

                    break;

                case "2":

                    break;

                case "3":

                    System.out.println("Hasta luego!");
                    break;

                case "4":

                    break;
                default:
                    System.out.println("Opcion no valido!");
            }

        } while (!"3".equals(opcion));

    }

// -------------------------
// MONSTRAR PRODUCTO
    public static void monstrarProducto() {
        for (Producto pro : productos) {
            System.out.println(productos.toString());
        }
    }

// -------------------------
// ELIMINAR PRODUCTO
    public static void eliminarProducto() {
        System.out.println("Introduce la referencia del producto: ");
        String productoEliminar = sc.nextLine();

        Producto eliminarProducto = null;
        for (Producto pro : productos) {
            if (pro.getReferencia().equals(productoEliminar)) {
                eliminarProducto = pro;
            }
        }

        if (eliminarProducto != null) {
            productos.remove(eliminarProducto);
            System.out.println("Producto eliminado!");
        } else {
            System.out.println("Producto no encontrado");
        }

    }

    // -------------------------
// GUARDAR PRODUCTOS EN FICHERO UNICODE
    public static void guardarProductoUNICODE() {
        try (FileWriter file = new FileWriter("src\\resources\\almacen.scv", false); BufferedWriter writer = new BufferedWriter(file)) {

            for (Producto pro : productos) {
                writer.write(pro.getReferencia() + "," + pro.getDescripcion() + "," + pro.getTipo() + "," + pro.getCantidad() + "," + pro.getPrecio() + "," + pro.getDescuento() + "," + pro.getIva() + "," + pro.isAplicarDto());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error al guardar: " + e.getMessage());
        }
    }

    // -------------------------
// CARGAR PRODUCTOS DESDE FICHERO UNICODE
    public static void cargarProductoUNICODE() {
        try (FileReader file = new FileReader("src\\resources\\almacen.scv"); BufferedReader reader = new BufferedReader(file);) {
            String linea = reader.readLine();
            while (linea != null) {

                String[] datos = linea.split(",");

                Producto pro = new Producto(linea, linea, linea, 0, 0, 0, 0, false);

                productosAlmacen.add(p2);

                linea = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Producto linea : productosAlmacen) {
            System.out.println(linea);
        }
    }

    // -------------------------
// GUARDAR PRODUCTOS EN FICHERO BINARIO
    public static void guardarProductoBINARIO() {
        try (FileOutputStream file = new FileOutputStream("C:\\Users\\daw1\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad2\\resources\\Almacen.dat", true); DataOutputStream writer = new DataOutputStream(file);) {

            for (Producto pro : productos) {
                writer.writeUTF(pro.getReferencia());
                writer.writeUTF(pro.getDescripcion());
                writer.writeUTF(pro.getTipo());
                writer.writeInt(pro.getCantidad());
                writer.writeDouble(pro.getPrecio());
                writer.writeInt(pro.getDescuento());
                writer.writeInt(pro.getIva());
                writer.writeBoolean(pro.isAplicarDto());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
