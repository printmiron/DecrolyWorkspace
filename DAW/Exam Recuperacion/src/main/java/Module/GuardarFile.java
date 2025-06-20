package Module;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GuardarFile {


    //Como tengo errores y problemas con mascotas, voy a guardar los prorprietarios como ejemplo
    public static void saveInFile(String fileName, List<Consulta> consultas) {

        try (FileOutputStream file = new FileOutputStream("src\\main\\resources\\"+fileName, false);
             ObjectOutputStream buffer = new ObjectOutputStream(file)){

            for (Consulta mascota : consultas) {
                buffer.writeObject(mascota);
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error: "+e.getMessage());
        }
    }

    public static List<Consulta> readFile(String fileName) {
        List<Consulta> consultas = new LinkedList<>();
        //Lectura del objeto
        boolean eof = false;
        try (FileInputStream file = new FileInputStream("src\\main\\resources\\"+fileName);
             ObjectInputStream reader = new ObjectInputStream(file)) {
            while (!eof) {
                Consulta m = (Consulta) reader.readObject();
                consultas.add(m);
            }
        } catch (EOFException e) {
            eof = true;
            System.out.println("Se ha leido el fichero completo");
        } catch (IOException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }

        return consultas;
    }


}
