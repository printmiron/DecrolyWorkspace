package Module;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GuardarFile {


    //Como tengo errores y problemas con mascotas, voy a guardar los prorprietarios como ejemplo
    public static void saveInFile(String fileName, List<Propietario> propietarios) {

        try (FileOutputStream file = new FileOutputStream("src\\main\\resources\\"+fileName, false);
             ObjectOutputStream buffer = new ObjectOutputStream(file)){

            for (Propietario propietario : propietarios) {
                buffer.writeObject(propietario);
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error: "+e.getMessage());
        }
    }

    public static List<Propietario> readFile(String fileName) {
        List<Propietario> propietarios = new LinkedList<>();
        //Lectura del objeto
        boolean eof = false;
        try (FileInputStream file = new FileInputStream("src\\main\\resources\\"+fileName);
             ObjectInputStream reader = new ObjectInputStream(file)) {
            while (!eof) {
                Propietario p = (Propietario) reader.readObject();
                propietarios.add(p);
            }
        } catch (EOFException e) {
            eof = true;
            System.out.println("Se ha leido el fichero completo");
        } catch (IOException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }

        return propietarios;
    }


}
