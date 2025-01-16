public class Agenda1 {

    private Contacto1 [] contactos;
    private int indiceActual;

    public Agenda1(Contacto1[] contactos) {
        this.contactos = new Contacto1[100];
        this.indiceActual = 0;
    }
    //clase agenda
    public boolean anadirContacto(Contacto1 c){
        boolean isAdd = false;
        if (c != null) {
            this.contactos[indiceActual] = c;
            this.indiceActual++;
            isAdd = true;
        }
        return isAdd;
    }

    public boolean eliminarContacto(String nombre){
        boolean isRemove = false;
        for (int i = 0; i < indiceActual; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Eliminado: " + contactos[i].getNombre());
                contactos[i] = contactos[indiceActual - 1];
                contactos[indiceActual - 1] = null;
                indiceActual--;
                break;
            }
        }
        return isRemove;
    }

    public boolean existeContacto (String nombre){
        for (Contacto1 contacto : contactos) {
            if (contacto != null && contacto.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void listarContactos() {
        if (indiceActual ==  0) {
            System.out.println("No hay contactos que mostrar");
        } else {
            for (int i = 0; i < indiceActual; i++) {
                System.out.println(contactos[i]);
            }
        }
    }

    public int buscaContacto(String nombre){
        
        for (int i = 0; i < indiceActual; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

}
