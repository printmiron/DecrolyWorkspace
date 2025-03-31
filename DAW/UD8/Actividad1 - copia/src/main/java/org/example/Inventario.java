package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Inventario {

    private static Scanner sc = new Scanner(System.in);

    private static List<Producto> productos = new ArrayList<Producto>();

    public static void main(String[] args) {

        Tipo.leerTipos();
        leerProductoUNICODE();

        String opcion = null;
        do {
            System.out.println("1) Mostrar todos los Productos en el Inventario");
            System.out.println("2) Buscar producto por referencia");
            System.out.println("3) Buscar productos por tipo");
            System.out.println("4) Buscar producto por cantidad");
            System.out.println("5) Insertar un nuevo producto");
            System.out.println("6) Eliminar Producto por referencia");
            System.out.println("7) Actualizar producto (descripción, cantidad, precio, descuento, AplicarDto)");
            System.out.println("8) Insertar un nuevo tipo de producto");
            System.out.println("9) Salir");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    mostrarProductos();
                    break;

                case "2":
                    buscarProductoReferencia();
                    break;

                case "3":
                    buscarProductoTipo();
                    break;

                case "4":
                    buscarProductoCantiad();
                    break;

                case "5":
                    insertarProducto();
                    break;

                case "6":
                    eliminarProducto();                    break;

                case "7":

                    break;

                case "8":
                    agregarNuevoTipo();
                    break;

                case "9":
                    escribirProductoUNICODE();
                    System.out.println("Hasta luego!");

                    break;
                default:
                    System.out.println("Opcion no valido!");
            }

        } while (!"9".equals(opcion));

    }


    private static void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos");
        }else{
            System.out.println("\nTotos los productos en el Inventario: ");
            for (Producto p : productos) {
                System.out.println(p.toString());
            }
        }

    }

    private static void buscarProductoReferencia() {
        System.out.println("Ingrese el referencia del producto: ");
        String prodReferencia = sc.nextLine();

        for (Producto p : productos) {
            if (p.getReferencia().equals(prodReferencia)){
                System.out.println("Encontrado: " + p.toString());
            }
            if (!prodReferencia.equals(p.getReferencia())){
                System.out.println("La referencia del producto no existe");
            }
        }

    }

    private static void buscarProductoTipo() {
        System.out.println("Ingrese el tipo del producto: ");
        String prodTipo = sc.nextLine();

        for (Producto p : productos) {
            if (p.getTipo().getId_tipo() == Integer.parseInt(prodTipo)){
                System.out.println(p.toString());
            }
            if (!prodTipo.equals(p.getTipo())){
                System.out.println("La tipo del producto no existe");
            }
        }
    }

    private static void buscarProductoCantiad() {
        System.out.println("Ingrese la cantidad del producto: ");
        String prodCantidad = sc.nextLine();

        for (Producto p : productos) {
            if (p.getCantidad()== Integer.parseInt(prodCantidad)){
                System.out.println(p.toString());
            }
            if (!prodCantidad.equals(p.getCantidad())){
                System.out.println("La Cantidad del producto no existe");
            }
        }
    }


    private static void insertarProducto() {

        System.out.print("Ingrese la referencia (EJ: ref-001): ");
        String referencia = validarEntrada("ref-\\d{3}");
        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese la descripcion: ");
        String descripcion = sc.nextLine();

        Tipo.monstarTipo();

        System.out.print("Ingrese el ID del tipo: ");
        int tipo = Integer.parseInt(sc.nextLine());


        Tipo tipoSeleccionado = null;

        try {
            tipoSeleccionado = new Tipo(tipo);
        }catch (Exception e){
            System.out.println("No existe este tipo: " + tipo);
        }

        System.out.print("Ingrese la cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());
        System.out.print("Ingrese el precio: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Ingrese el descuento: ");
        int descuento = Integer.parseInt(sc.nextLine());
        System.out.print("Ingrese el IVA: ");
        int iva = Integer.parseInt(sc.nextLine());
        System.out.print("Aplicar descuento? (si/no): ");
        String aplicarDtoString = sc.nextLine().trim().toLowerCase();
        boolean aplicarDto = aplicarDtoString.equals("si");

        Producto p = new Producto(productos.size() + 1, referencia, nombre, descripcion, tipoSeleccionado, cantidad, precio, descuento, iva, aplicarDto);
        productos.add(p);
        System.out.println("Producto creado correctamente.");
    }



    private static void eliminarProducto() {
        System.out.println("Ingrese el referencia del producto: ");
        String referencia = sc.nextLine();

        Producto eliminarProducto = null;
        for (Producto pro : productos) {
            if (pro.getReferencia().equals(referencia)){
                eliminarProducto = pro;
            }
        }

        if (eliminarProducto == null){
            System.out.println("El referencia del producto no existe");
        }else {
            productos.remove(eliminarProducto);
        }

    }




    public static void agregarNuevoTipo(){
        Tipo.monstarTipo();
        System.out.println("Ingrese el id sigunente tipo de producto: ");
        int id = Integer.parseInt(sc.nextLine());


        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        Tipo.agregarTipo(id, nombre);

    }





    public static void leerProductoUNICODE() {

        try (FileReader file = new FileReader("src/main/resources/application.dat"); BufferedReader buffer = new BufferedReader(file);) {
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] datos = linea.split("/");

                Producto p = new Producto datos[0],
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4], );

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void escribirProductoUNICODE() {
        try (FileWriter file = new FileWriter("src/main/resources/application.dat", false); BufferedWriter writer = new BufferedWriter(file)) {
            for (Producto p : productos) {

                String aplicarDtoStr = p.isAplicarDto() ? "si" : "no";

                writer.write(p.getId() + "/" + p.getReferencia() + "/" + p.getNombre() + "/" + p.getDescripcion() + "/"
                        + p.getTipo().getId_tipo() + "/" + p.getCantidad() + "/" + p.getPrecio() + "/" +  p.getDescuento() + "/" +  p.getIva() + "/" + aplicarDtoStr);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error al guardar: " + e.getMessage());
        }
    }


    private static String validarEntrada(String regex) {
        String entrada;
        Pattern pattern = Pattern.compile(regex);
        do {
            entrada = sc.nextLine().trim();
            if (!pattern.matcher(entrada).matches()) {
                System.out.println("Formato incorrecto, intenta de nuevo!");
            }
        } while (!pattern.matcher(entrada).matches());
        return entrada;
    }





























    }



