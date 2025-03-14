
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

    
        leerProductoBINARIO();

        String opcion = null;
        do {
            System.out.println("1) Mostrar Productos en el Inventario");
            System.out.println("2) Eliminar Producto por referencia");
            System.out.println("3) Guardar y Salir");
            System.out.println("4) Crear producto");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    monstrarProducto();
                    break;

                case "2":
                    eliminarProducto();
                    break;

                case "3":
                    escribirProductoBINARIO();
                    escribirProductoUNICODE();
                    System.out.println("Hasta luego!");
                    break;

                case "4":
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

                    Producto p1 = new Producto(referencia, descripcion, tipo, cantidad, precio, descuento, iva, aplicarDto);
                    productos.add(p1);

                    System.out.println("Producto almacenado bien!");
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

    public static void leerProductoUNICODE() {
        LinkedList<Producto> lineas = null;
        lineas = new LinkedList<Producto>();

        try (FileReader file = new FileReader("C:\\Users\\daw1\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad4\\src\\resources\\productos.scv"); BufferedReader buffer = new BufferedReader(file);) {
            String linea = null;
            do {
                linea = buffer.readLine();

                if (linea != null) {

                    String[] datos = linea.split("/");
                    Producto p = new Producto(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]), Double.parseDouble(datos[4]), Integer.parseInt(datos[5]), Integer.parseInt(datos[6]), Boolean.parseBoolean(datos[7]));

                    lineas.add(p);
                }

            } while (linea != null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escribirProductoUNICODE() {
        try (FileWriter file = new FileWriter("C:\\Users\\daw1\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad4\\src\\resources\\productos.scv", false); BufferedWriter writer = new BufferedWriter(file)) {
            for (Producto pro : productos) {
                writer.write(pro.getReferencia() + "/" + pro.getDescripcion() + "/" + pro.getTipo() + "/" + pro.getCantidad() + "/" + pro.getPrecio() + "/" + pro.getDescuento() + "/" + pro.getIva() + "/" + pro.isAplicarDto());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error al guardar: " + e.getMessage());
        }
    }

    public static void leerProductoBINARIO() {
        boolean eof = false;
        //Lectura de fichero binario
        try (FileInputStream file = new FileInputStream("C:\\Users\\daw1\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad4\\src\\resources\\almacen.dat"); DataInputStream reader = new DataInputStream(file);) {
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

    public static void escribirProductoBINARIO() {
        try (FileOutputStream file = new FileOutputStream("C:\\Users\\daw1\\Documents\\DecrolyWorkspace\\DAW\\UD6\\Actividad4\\src\\resources\\almacen.dat", false); DataOutputStream writer = new DataOutputStream(file);) {

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
