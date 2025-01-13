public class Agenda {

    private Contacto [] contactos;
    private int indiceActual;

    public Agenda(Contacto[] contactos) {
        this.contactos = new Contacto[100];
        this.indiceActual = 0;
    }
    //clase agenda
    public boolean anadirContacto(Contacto c){
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
                System.out.println("Elimado: " + contactos[i].getNombre());
                contactos[i] = contactos[indiceActual - 1];
                contactos[indiceActual - 1] = null;
                indiceActual--;
            }
        }
        return isRemove;
    }

    //public boolean existeContacto (String nombre){
        //return contactos.exist(nombre);
    //}

    public void listarContactos() {
        if (contactos == null) {
            System.out.println("No hay contactos que mostrar");
        } else {
            System.out.println(contactos.toString());
        }
    }

    public int buscaContacto(String nombre){
        
        for (int i = 0; i < indiceActual; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println();
            }
        }
        return 0;
    }

}
