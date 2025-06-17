package Module;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tipo implements Serializable {
    private static final long serialVersionUID = 2L;

    private int idTipo;
    private String tipo;

    public Tipo(int idTipo, String tipo) {
        this.idTipo = idTipo;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    //cargar los tipos desde base de datos
    private static List<Tipo> tipos = new ArrayList<>();

    public static void cargarTiposDesdeBD(Connection connection) throws SQLException {

        String sql = "SELECT idTipo, Tipo FROM Tipo";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            tipos.clear();
            while (rs.next()) {
                tipos.add(new Tipo(rs.getInt("idTipo"), rs.getString("Tipo")));
            }
        }
    }

    public static List<Tipo> getTipos() {
        return tipos;
    }

    public static Tipo getPorId(int id) {
        return tipos.stream().filter(t -> t.getIdTipo() == id).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}
