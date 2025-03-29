package org.example;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Tipo {
    //!!! cuando creamos nuevos tipos y asignamos algun producto a este nueve tipo durante la session, despues de reiniar,
    //la programa no encuantra el nuevo tipo por eso creo un archivo con "tipos.dat" para que despues de
    //reinicar pueda encontrar y leer bien los productos sin errores
    private static Map<Integer, String> tipos = new TreeMap<>();

    private int id_tipo;
    private String nombre;

    public Tipo(int id_tipo) {
        if (tipos.containsKey(id_tipo)) {
            this.id_tipo = id_tipo;
            this.nombre = tipos.get(id_tipo);
        }else {
            throw new RuntimeException("Tipo no encontrado");
        }

    }

    public int getId_tipo() {
        return id_tipo;
    }

    public String getNombre() {
        return nombre;
    }

    //inicializar los tipos predeterminados, acceder a ellos aunque no tenemos el objeto
    static {
        tipos.put(1, "Electronica");
        tipos.put(2, "Alimentacion");
        tipos.put(3, "Ropa");
        tipos.put(4, "Deportes");
    }

    public static void agregarTipo(int id_tipo, String nombre) {
        if (!tipos.containsKey(id_tipo)) {
            tipos.put(id_tipo, nombre);
            System.out.println("Tipo " + id_tipo + " agregado");
            escribirTipos();
        }else {
            System.out.println("Tipo " + id_tipo + " ya existente");
        }
    }

    public static void monstarTipo() {
        System.out.println("\nTipos disponibles: ");
        for (Map.Entry<Integer, String> entry : tipos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }



    public static void leerTipos() {

        try (FileReader file = new FileReader("src/main/resources/tipos.dat"); BufferedReader buffer = new BufferedReader(file);) {
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] datos = linea.split("/");

                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];

                tipos.put(id, nombre);

            }
        } catch (IOException e) {
            System.out.println("Error al leer!");
        }
    }



    public static void escribirTipos() {
        try (FileWriter file = new FileWriter("src/main/resources/tipos.dat", false); BufferedWriter writer = new BufferedWriter(file)) {
            for (Map.Entry<Integer, String> entry : tipos.entrySet()) {

                writer.write(entry.getKey() + ": " + entry.getValue() );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error al guardar: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return id_tipo + " - " + nombre;
    }
}
