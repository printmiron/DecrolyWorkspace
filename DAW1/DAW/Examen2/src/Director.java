public class Director extends Persona{

    private String numeroTel;
    private boolean reunido;
    private boolean fueraOficina;


    public Director(String nombre, String fechaNacimiento, String dni, String direccion, String numeroTel,
            boolean reunido, boolean fueraOficina) {
        super(nombre, fechaNacimiento, dni, direccion);
        this.numeroTel = numeroTel;
        this.reunido = reunido;
        this.fueraOficina = fueraOficina;
    }

    public String getNumeroTel() {
        return numeroTel;
    }


    public boolean isReunido() {
        return reunido;
    }


    public boolean isFueraOficina() {
        return fueraOficina;
    }



    @Override
    public String toString() {
        return super.toString() + "Director [numeroTel=" + numeroTel + ", reunido=" + reunido + ", fueraOficina=" + fueraOficina + "]";
    }

    
    
}
