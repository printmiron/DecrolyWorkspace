module com.example.vetdaw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.vetdaw to javafx.fxml;
    exports com.example.vetdaw;
}