package Module;

import java.time.LocalDateTime;

public class Mascota {
    private String pasaporte;
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private Double peso;
    private Tipo tipo;
    private Propietario propietario;//Por esta linea sa comoplica para mi por guardar las consultas lo mismo como con Consultas, pero fui intentos

    public Mascota(String pasaporte, String nombre, LocalDateTime fechaNacimiento, Double peso, Tipo tipo, Propietario propietario) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.tipo = tipo;
        this.propietario = propietario;
    }

    public Mascota() {

    }

    public Mascota(MascotaBuilder mascotaBuilder) {
        this.pasaporte = mascotaBuilder.getPasaporte();
        this.nombre = mascotaBuilder.getNombre();
        this.fechaNacimiento = mascotaBuilder.getFechaNacimiento();
        this.peso = mascotaBuilder.getPeso();
        this.tipo = mascotaBuilder.getTipo();
        this.propietario = mascotaBuilder.getPropietario();
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "pasaporte='" + pasaporte + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", peso=" + peso +
                ", tipo='" + tipo + '\'' +
                ", propietario=" + propietario +
                '}';
    }

public static MascotaBuilder builder() {
        return new MascotaBuilder();
}
}
