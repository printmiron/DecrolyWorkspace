module org.example.proyectometaplay {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.proyectometaplay to javafx.fxml;
    exports org.example.proyectometaplay;
}