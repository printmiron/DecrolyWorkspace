module com.example.pavelmironexamen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pavelmironexamen to javafx.fxml;
    exports com.example.pavelmironexamen;
}