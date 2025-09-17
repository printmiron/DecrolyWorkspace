package org.example.demofx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Persona;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Persona persona;

    @FXML
    private VBox mainPane;


    //CAMPOS DE TEXTO FORMULARIO
    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField edadTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField telefonoTextField;


    //BUTONES
    @FXML
    private Button crearButton;

    @FXML
    private Button cancelarButton;




    //EVENTOS
    @FXML
    protected void onSaveButtonAccion(ActionEvent event) {
        persona = new Persona();

        try{
            persona.setNombre(nombreTextField.getText());
            persona.setApellido(apellidoTextField.getText());
            persona.setEdad(Integer.parseInt(edadTextField.getText()));
            persona.setEmail(emailTextField.getText());
            persona.setTelefono(telefonoTextField.getText());

            mainPane.setVisible(false);

        }catch (NumberFormatException e){
            edadTextField.setText("");
            edadTextField.setPromptText("Introduce un numero");


        }


    }

    @FXML
    protected void onCloseButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombreTextField.setPromptText("Nombre");
        apellidoTextField.setPromptText("Apellido");
        edadTextField.setPromptText("Edad");
        emailTextField.setPromptText("Email");
        telefonoTextField.setPromptText("Telefono");
    }
}