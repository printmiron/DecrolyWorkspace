module com.example.simulacroexam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.simulacroexam to javafx.fxml;
    exports com.example.simulacroexam;
}