package Module;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mascota implements Serializable {
    private static final long serialVersionUID = 3L;

    private String pasaporte;
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private Double peso;
    private Tipo tipo;
    private Propietario propietario;

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
        return nombre + " - " + pasaporte;
    }




    public static MascotaBuilder builder() {
        return new MascotaBuilder();
}


    private static List<Mascota> mascotas = new ArrayList<>();

    public static void cargarMascotasDesdeBD(Connection connection) throws SQLException {
        String sql = "SELECT Pasaporte, Nombre, FechaNacimiento, Peso, Tipo_idTipo, Propietario_dni FROM Mascota";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            mascotas.clear();
            while (rs.next()) {
                String pasaporte = rs.getString("Pasaporte");
                String nombre = rs.getString("Nombre");
                LocalDateTime fechaNacimiento = rs.getTimestamp("FechaNacimiento").toLocalDateTime();
                double peso = rs.getDouble("Peso");
                int tipoId = rs.getInt("Tipo_idTipo");
                String propietarioDni = rs.getString("Propietario_dni");

                // Obtener objetos Tipo y Propietario a partir de sus IDs
                Tipo tipo = Tipo.getPorId(tipoId);
                Propietario propietario = Propietario.getPorDni(propietarioDni);

                Mascota mascota = new Mascota(pasaporte, nombre, fechaNacimiento, peso, tipo, propietario);
                mascotas.add(mascota);
            }
        }
    }

    public static List<Mascota> getMascotas() {
        return mascotas;
    }





}
