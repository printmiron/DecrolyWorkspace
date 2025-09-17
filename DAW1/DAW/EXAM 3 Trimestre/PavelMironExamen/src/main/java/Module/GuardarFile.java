package Module;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GuardarFile {


    //Como tengo errores y problemas con mascotas, voy a guardar los prorprietarios como ejemplo
    public static void saveInFile(String fileName, List<Mascota> mascotas) {

        try (FileOutputStream file = new FileOutputStream("src\\main\\resources\\"+fileName, false);
             ObjectOutputStream buffer = new ObjectOutputStream(file)){

            for (Mascota mascota : mascotas) {
                buffer.writeObject(mascota);
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error: "+e.getMessage());
        }
    }

    public static List<Mascota> readFile(String fileName) {
        List<Mascota> mascotas = new LinkedList<>();
        //Lectura del objeto
        boolean eof = false;
        try (FileInputStream file = new FileInputStream("src\\main\\resources\\"+fileName);
             ObjectInputStream reader = new ObjectInputStream(file)) {
            while (!eof) {
                Mascota m = (Mascota) reader.readObject();
                mascotas.add(m);
            }
        } catch (EOFException e) {
            eof = true;
            System.out.println("Se ha leido el fichero completo");
        } catch (IOException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }

        return mascotas;
    }


}
