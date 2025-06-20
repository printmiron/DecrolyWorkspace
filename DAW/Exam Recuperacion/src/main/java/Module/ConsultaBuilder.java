package Module;

import java.time.LocalDateTime;

public class ConsultaBuilder {

    private int id;
    private String observaciones;
    private LocalDateTime fecha;
    private Tipo tipo;
    private Paciente paciente;
    private Doctor doctor;

    ConsultaBuilder() {
    }

    public int getId() {
        return id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public ConsultaBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ConsultaBuilder Observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }
    public ConsultaBuilder Fecha(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }
    public ConsultaBuilder Tipo(Tipo tipo) {
        this.tipo = tipo;
        return this;
    }
    public ConsultaBuilder Paciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }
    public ConsultaBuilder Doctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public Consulta build() {
        Consulta consulta = new Consulta(this);
        return consulta;
    }
}
