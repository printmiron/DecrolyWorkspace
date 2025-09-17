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

    private int id;
    private String tipo;

    public Tipo(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //cargar los tipos desde base de datos
    private static List<Tipo> tipos = new ArrayList<>();

    public static void cargarTiposDesdeBD(Connection connection) throws SQLException {

        String sql = "SELECT id, Tipo FROM TipoConsulta";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            tipos.clear();
            while (rs.next()) {
                tipos.add(new Tipo(rs.getInt("id"), rs.getString("Tipo")));
            }
        }
    }

    public static List<Tipo> getTipos() {
        return tipos;
    }

    public static Tipo getPorId(int id) {
        return tipos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return this.tipo;
    }
}

