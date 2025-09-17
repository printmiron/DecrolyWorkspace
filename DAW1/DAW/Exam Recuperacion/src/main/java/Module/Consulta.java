package Module;

import java.time.LocalDateTime;

public class Consulta {

    private int id;
    private String observaciones;
    private LocalDateTime fecha;
    private Tipo tipo;
    private Paciente paciente;
    private Doctor doctor;

    public Consulta(int id, String observaciones, LocalDateTime fecha, Tipo tipo, Paciente paciente, Doctor doctor) {
        this.id = id;
        this.observaciones = observaciones;
        this.fecha = fecha;
        this.tipo = tipo;
        this.paciente = paciente;
        this.doctor = doctor;
    }

    public Consulta() {
    }

    public Consulta (ConsultaBuilder consultaBuilder) {
        this.id = consultaBuilder.getId();
        this.observaciones = consultaBuilder.getObservaciones();
        this.fecha = consultaBuilder.getFecha();
        this.tipo = consultaBuilder.getTipo();
        this.paciente = consultaBuilder.getPaciente();
        this.doctor = consultaBuilder.getDoctor();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public static ConsultaBuilder builder() {
        return new ConsultaBuilder();
    }
}
