package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    public final String DRIVER = "com.mysql.jdbc.Driver";
    public final String URL = "jdbc:mysql://localhost:3306/";
    public final String USER = "root";
    public final String PASS = "";

    public Connection ConexionMySQL() {
        Connection con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return con;
    }


}
