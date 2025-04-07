package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Inventario {

    private static Scanner sc = new Scanner(System.in);

    private static AccessDCuniverseSQL BD = new AccessDCuniverseSQL();

    public static void main(String[] args) {



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
                    monstrarProductos();
                    break;

                case "2":
                    productoReferencia();
                    break;

                case "3":
                    productoTipo();
                    break;

                case "4":
                    productoCant();
                    break;

                case "5":
                    ingresarProducto();
                    break;

                case "6":
                    eliminarProducto();
                    break;

                case "7":
                    actualizarProducto();
                    break;

                case "8":
                    insertarTipo();
                    break;

                case "9":

                    System.out.println("Hasta luego!");

                    break;
                default:
                    System.out.println("Opcion no valido!");
            }

        } while (!"9".equals(opcion));

    }



    public static void monstrarProductos() {
        List<Producto> productos = BD.getProductos();
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el Inventario");
        }
    }

    public static void productoReferencia() {
        System.out.println("Ingrese el referencia del producto: ");
        String referencia = sc.nextLine();

        List<Producto> productos = BD.getProductoPorRef(referencia);

        for (Producto producto : productos) {
                System.out.println(producto.toString());
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el Inventario");
        }

    }

    public static void productoTipo() {
        System.out.println("Ingrese el tipo del producto: ");
        String tipo = sc.nextLine();

        List<Producto> productos = BD.getProductoPorTipo(tipo);
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el Inventario");
        }

    }

    public static void productoCant() {
        System.out.println("Ingrese la cantidad del producto: ");
        String cant = sc.nextLine();

        List<Producto> productos = BD.getProductoPorCant(cant);

        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el Inventario");
        }

    }

    //insertar un producto
    public static void ingresarProducto() {
        System.out.println("Ingrese el referencia del producto: ");
        String referencia = sc.nextLine();

        System.out.println("Ingrese el nombre de producto: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el descripcion de producto: ");
        String descripcion = sc.nextLine();

        List<Tipo> tipos = BD.getTipos();
        System.out.println("Tipos disponibles:");
        for (Tipo tipo : tipos) {
            System.out.println(tipo.getId_tipo() + " - " + tipo.getNombre());
        }

        System.out.println("Ingrese el id del tipo de producto: ");
        int tipoId = Integer.parseInt(sc.nextLine());
        Tipo tipo = BD.getTipoPorId(tipoId);

        System.out.println("Ingrese el cantidad de producto: ");
        int cantidad = sc.nextInt();

        System.out.println("Ingrese el precio de producto: ");
        double precio = sc.nextDouble();

        System.out.println("Ingrese el descuento de producto: ");
        int descuento = sc.nextInt();

        System.out.println("Ingrese la iva de producto: ");
        int iva = sc.nextInt();

        System.out.println("Ingrese el aplicar DTO: ");
        boolean aplicarDto = sc.nextBoolean();

        Producto p = new Producto(-1, referencia, nombre, descripcion, tipo, cantidad, precio, iva, descuento, aplicarDto);

        BD.insertarProducto(p);
        System.out.println("El producto se ha registrado");


    }

    public static void eliminarProducto() {
        System.out.println("Ingrese el referencia del producto: ");
        String referenciaEliminar = sc.nextLine();

        BD.eliminarProducto(referenciaEliminar);
        System.out.println("El producto se ha eliminado");
    }


    public static void actualizarProducto() {

        monstrarProductos();

        List<Producto> productos = BD.getProductos();

        System.out.println("Elige que producto quieres modificar por id:");
        int id = sc.nextInt();

        Producto seleccionarProducto = null;

        for (Producto producto : productos) {
            if (producto.getId() == id) {
                seleccionarProducto = producto;
            }
        }

        sc.nextLine();

        System.out.println("Introduce la descripcion modificada: ");
        String descripcionMod = sc.nextLine();


        System.out.println("Introduce la cantidad modificada: ");
        int cantidadMod = sc.nextInt();

        System.out.println("Introduce la precio modificada: ");
        double precioMod = sc.nextDouble();

        System.out.println("Introduce la descuento modificada: ");
        int descuentoMod = sc.nextInt();

        System.out.println("Introduce la aplicar DTO modificada: ");
        boolean aplicarDtoMod = sc.nextBoolean();

        seleccionarProducto.setDescripcion(descripcionMod);
        seleccionarProducto.setCantidad(cantidadMod);
        seleccionarProducto.setPrecio(precioMod);
        seleccionarProducto.setDescuento(descuentoMod);
        seleccionarProducto.setAplicarDto(aplicarDtoMod);
        BD.actualizarProducto(seleccionarProducto);

        System.out.println("Producto actualizado!");
    }



    public static void insertarTipo() {

        List<Tipo> tipos = BD.getTipos();
            for (Tipo t : tipos) {
                System.out.println(t.toString());
            }

        System.out.println("Introduce el nombre del nuevo tipo:");
        String nombreTipo = sc.nextLine();

        Tipo tipo = new Tipo(-1, nombreTipo);
        tipo.setNombre(nombreTipo);

        BD.insertarTipo(tipo);

    }









}










