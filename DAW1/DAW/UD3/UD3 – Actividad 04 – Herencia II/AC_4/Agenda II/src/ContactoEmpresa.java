public class ContactoEmpresa extends Contacto{

    private String paginaWeb;

    public ContactoEmpresa(String nombre, String numeroT, String paginaWeb) {
        super(nombre, numeroT);
        this.paginaWeb = paginaWeb;
    }

    public String getPaginaWeb() {
        return this.paginaWeb;
    }
 
    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    @Override
    public String toString() {
        return super.toString() + " [paginaWeb=" + paginaWeb + "]";
    }
    

}
