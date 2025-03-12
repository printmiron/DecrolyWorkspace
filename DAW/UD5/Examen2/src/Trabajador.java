public class Trabajador extends Persona{

    private String numeroSS;
    private String emailEmpresa;
    private String salario;
    private String departamento;
    private boolean enOficina;
    
    public Trabajador(String nombre, String fechaNacimiento, String dni, String direccion, String numeroSS,
            String emailEmpresa, String salario, String departamento, boolean enOficina) {
        super(nombre, fechaNacimiento, dni, direccion);
        this.numeroSS = numeroSS;
        this.emailEmpresa = emailEmpresa;
        this.salario = salario;
        this.departamento = departamento;
        this.enOficina = enOficina;
    }

    public String getNumeroSS() {
        return numeroSS;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public String getSalario() {
        return salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public boolean isEnOficina() {
        return enOficina;
    }

    @Override
    public String toString() {
        return super.toString() + "Trabajador [numeroSS=" + numeroSS + ", emailEmpresa=" + emailEmpresa + ", salario=" + salario
                + ", departamento=" + departamento + ", enOficina=" + enOficina + "]";
    }

   

    

}
