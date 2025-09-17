package Module;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MascotaBuilder {

    private String pasaporte;
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private Double peso;
    private Tipo tipo;
    private Propietario propietario;

    public MascotaBuilder() {

    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPeso() {
        return peso;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public MascotaBuilder pasaporte(String pasaporte) {
            this.pasaporte = pasaporte;
            return this;
    }
    public MascotaBuilder nombre(String nombre) {
            this.nombre = nombre;
            return this;
    }
    public MascotaBuilder peso(Double peso) {
            this.peso = peso;
            return this;
    }
    public MascotaBuilder tipo(Tipo tipo) {
            this.tipo = tipo;
            return this;
    }
    public MascotaBuilder propietario(Propietario propietario) {
            this.propietario = propietario;
            return this;
    }

    public MascotaBuilder fechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public Mascota build() {
        Mascota mascota = new Mascota(this);
        return mascota;
    }
}
