package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManagerSQL {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "schema_ac1";
    private static final String USER = "developer";
    private static final String PASS = "developer";

    public static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL+SCHEMA, USER, PASS);

        } catch (ClassNotFoundException e) {
            System.out.println("Fallo de acceso: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Fallo en SQL: " + e.getMessage());
        }

        return con;
    }
}
