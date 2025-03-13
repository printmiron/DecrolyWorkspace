
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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Inventario {

    private static final Scanner sc = new Scanner(System.in);

    private static List<Producto> productos = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        cargarProductoUNICODE();
        cargarProductoBINARIO();
        

        String opcion = null;
        do {
            System.out.println("1) Mostrar Productos en el Inventario");
            System.out.println("2) Eliminar Producto por referencia");
            System.out.println("3) Guardar y Salir");
            System.out.println("4) Guardar Libros en el fichero");
            System.out.println("5) Crear producto");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    monstrarProducto();
                    break;

                case "2":
                    eliminarProducto();
                    break;

                case "3":
                    guardarProductoUNICODE();
                    guardarProductoBINARIO();
                    System.out.println("Hasta luego!");
                    break;

                case "4":
                    guardarProductoUNICODE();
                    guardarProductoBINARIO();
                    break;

                case "5": 
                    System.out.println("Intoduce la referencia del producto: ");
                    String referencia = sc.nextLine();

                    System.out.println("Descripcion: ");
                    String descripcion = sc.nextLine();

                    System.out.println("Intoduce tipo de producto: ");
                    String tipo = sc.nextLine();

                    System.out.println("Intoduce su cantidad: ");
                    int cantidad = sc.nextInt();

                    System.out.println("Intoduce su prcio: ");
                    double precio = sc.nextDouble();

                    System.out.println("Intoduce descuento si tiene: ");
                    int descuento = sc.nextInt();

                    System.out.println("Intoduce la iva: ");
                    int iva = sc.nextInt();

                    System.out.println("Aplicar Dto?: ");
                    boolean aplicarDto = sc.nextBoolean();
                   
                    

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
        try (FileWriter file = new FileWriter("src\\resources\\productos.scv", false); BufferedWriter writer = new BufferedWriter(file)) {

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
        try (FileReader file = new FileReader("src\\resources\\productos.scv"); BufferedReader reader = new BufferedReader(file);) {
            String linea = reader.readLine();
            while (linea != null) {

                String[] datos = linea.split(",");

                Producto pro = new Producto(linea, linea, linea, 0, 0, 0, 0, false);

                productos.add(pro);

                linea = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Producto linea : productos) {
            System.out.println(linea);
        }
    }

    // -------------------------
// GUARDAR PRODUCTOS EN FICHERO BINARIO
    public static void guardarProductoBINARIO() {
        try (FileOutputStream file = new FileOutputStream("src\\resources\\almacen.dat", true); DataOutputStream writer = new DataOutputStream(file);) {

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


        // -------------------------
// CARGAR PRODUCTOS EN FICHERO BINARIO
    public static void cargarProductoBINARIO() {
        productos.clear();
        boolean eof = false;
        //Lectura de fichero binario
        try (FileInputStream file = new FileInputStream("src\\resources\\almacen.dat"); DataInputStream reader = new DataInputStream(file);) {

            while (!eof) {

                String referencia = reader.readUTF();
                String descripcion = reader.readUTF();
                String tipo = reader.readUTF();
                int cantidad = reader.readInt();
                double precio = reader.readDouble();
                int descuento = reader.readInt();
                int iva = reader.readInt();
                boolean aplicarDto = reader.readBoolean();
                Producto pro = new Producto(referencia, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto);
                productos.add(pro);

            }

        } catch (EOFException e) {
            eof = true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos en Almacen!");
        } else {
            for (Producto pro : productos) {
                System.out.println(pro);
            }
        }
    }

}
