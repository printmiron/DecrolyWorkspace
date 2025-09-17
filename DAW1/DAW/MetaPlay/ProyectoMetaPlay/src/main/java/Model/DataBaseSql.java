package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseSql {

    private static  String DRIVER;
    private static  String URL;
    private static  String SCHEMA;
    private static  String USER;
    private static  String PASS;

    public static Connection getConnection() {
        Connection con = null;

        try (FileReader file = new FileReader("src/main/resources/application.dat"); BufferedReader reader = new BufferedReader(file);) {
            String linea = reader.readLine();
            while (linea != null) {

                String[] datos = linea.split(",");

                DRIVER = datos[0];
                URL = datos[1];
                SCHEMA = datos[2];
                USER = datos[3];
                PASS = datos[4];

                linea = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL+SCHEMA, USER, PASS);
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return con;
    }



}
