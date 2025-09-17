module com.example.examrecuperacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.examrecuperacion to javafx.fxml;
    exports com.example.examrecuperacion;
}