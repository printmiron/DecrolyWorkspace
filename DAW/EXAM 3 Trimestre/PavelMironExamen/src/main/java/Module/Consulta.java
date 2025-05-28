package Module;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Consulta implements Serializable {
    private static final long serialVersionUID = 2L;

    private int id;
    private LocalDateTime fechaConsulta;
    private int duracion;
    private String observaciones;
    private Mascota pasaporteConsulta;
    private Propietario dniConsulta; //Por esta linea sa comoplica para mi por guardar las consultas lo mismo como con Mascotas

    public Consulta(int id, LocalDateTime fechaConsulta, int duracion, String observaciones, Mascota pasaporteConsulta, Propietario dniConsulta) {
        this.id = id;
        this.fechaConsulta = fechaConsulta;
        this.duracion = duracion;
        this.observaciones = observaciones;
        this.pasaporteConsulta = pasaporteConsulta;
        this.dniConsulta = dniConsulta;
    }

    public Consulta() {

    }

    public Consulta(ConsultaBuilder consultaBuilder) {
        this.id = consultaBuilder.getId();
        this.fechaConsulta = consultaBuilder.getFechaConsulta();
        this.duracion = consultaBuilder.getDuracion();
        this.observaciones = consultaBuilder.getObservaciones();
        this.pasaporteConsulta = consultaBuilder.getPasaporteConsulta();
        this.dniConsulta = consultaBuilder.getDniConsulta();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Mascota getPasaporteConsulta() {
        return pasaporteConsulta;
    }

    public void setPasaporteConsulta(Mascota pasaporteConsulta) {
        this.pasaporteConsulta = pasaporteConsulta;
    }

    public Propietario getDniConsulta() {
        return dniConsulta;
    }

    public void setDniConsulta(Propietario dniConsulta) {
        this.dniConsulta = dniConsulta;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                "fechaConsulta=" + fechaConsulta +
                ", duracion=" + duracion +
                ", observaciones='" + observaciones + '\'' +
                ", pasaporteConsulta=" + pasaporteConsulta +
                ", dniConsulta=" + dniConsulta +
                '}';
    }


    public static ConsultaBuilder builder() {
        return new ConsultaBuilder();
    }
}
