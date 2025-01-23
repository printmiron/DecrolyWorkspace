
import java.util.HashMap;

public class aAgenda {

    private HashMap<String, cContacto> contactosEnAgenda;
    private int indiceActual;

    public aAgenda(HashMap<String, cContacto> contactosEnAgenda) {
        this.contactosEnAgenda = new HashMap<>();
        this.indiceActual = 0;
    }

    public boolean anadeContacto(String nombre, cContacto contacto) {
        if (contactosEnAgenda.containsKey(nombre)) {
            System.out.println("Contacto con este nombre ya existe");
            return false;
        } else {
            contactosEnAgenda.put(nombre, contacto);
            System.out.println("Contacto registardo correcto");
            return true;
        }
    }
//cambiar if y else cotainsKey

    public void buscaContacto(String nombre) {
        if (contactosEnAgenda.containsKey(nombre)) {
            for (cContacto contactos : contactosEnAgenda.values()) {
                System.out.println(contactos);
            }
        } else {
            System.out.println("No hay contacto en agenda!");
        }
    }

    public void eliminaContacto(String nombre) {
        if (contactosEnAgenda.containsKey(nombre)) {
            contactosEnAgenda.remove(nombre);
            System.out.println("Contacto eliminado");
        } else {
            System.out.println("No hay contacto con ese nombre");
        }
    }

    public void visualizaAgenda(){
        if (contactosEnAgenda.isEmpty()) {
            System.out.println("No hay contactos en agenda!");
        }else{
            for (String nombre : contactosEnAgenda.keySet()) {
              cContacto contactos = contactosEnAgenda.get(nombre);
              System.out.println("[Nombre = " + nombre + "] " + contactos);  
            }
        }
    }
    








}
