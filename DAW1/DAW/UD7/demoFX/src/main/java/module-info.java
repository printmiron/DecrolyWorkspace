module org.example.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.demofx to javafx.fxml;
    exports org.example.demofx;
}