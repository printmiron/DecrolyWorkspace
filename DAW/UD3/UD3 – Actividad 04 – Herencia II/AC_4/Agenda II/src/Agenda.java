
import java.util.ArrayList;

public class Agenda {

    private ArrayList<Contacto> contactos;

    public Agenda() {
        this.contactos = new ArrayList<>();
    }
    //clase agenda
    public boolean anadirContacto(Contacto c){
        if (c != null) {
            return false;
        }
        contactos.add(c);
        return true;
    }

    public boolean eliminarContacto(String nombre){
        
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                contactos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean existeContacto (String nombre){
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos que mostrar");
        } else {
            for (Contacto c: contactos) {
                System.out.println(c.toString());
            }
        }
    }

    public int buscaContacto(String nombre){
        
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

}
