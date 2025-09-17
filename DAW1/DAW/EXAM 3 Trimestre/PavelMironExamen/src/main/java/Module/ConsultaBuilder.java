package Module;

import java.time.LocalDateTime;

public class ConsultaBuilder {

    private int id;
    private LocalDateTime fechaConsulta;
    private int duracion;
    private String observaciones;
    private Mascota pasaporteConsulta;
    private Propietario dniConsulta;

    public ConsultaBuilder() {

    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Mascota getPasaporteConsulta() {
        return pasaporteConsulta;
    }

    public Propietario getDniConsulta() {
        return dniConsulta;
    }

    public ConsultaBuilder id(int id) {
        this.id = id;
        return this;
    }
    public ConsultaBuilder fechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
        return this;
    }
    public ConsultaBuilder duracion(int duracion) {
        this.duracion = duracion;
        return this;
    }
    public ConsultaBuilder observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }
    public ConsultaBuilder pasaporteConsulta(Mascota pasaporteConsulta) {
        this.pasaporteConsulta = pasaporteConsulta;
        return this;
    }
    public ConsultaBuilder dniConsulta(Propietario dniConsulta) {
        this.dniConsulta = dniConsulta;
        return this;
    }

    public Consulta build() {
        Consulta consulta = new Consulta(this);
        return consulta;
    }
}
