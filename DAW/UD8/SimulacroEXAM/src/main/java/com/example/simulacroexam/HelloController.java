package com.example.simulacroexam;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import Module.Persona;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController {

    private Persona persona;
    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    //VBox`s
    @FXML
    private VBox mainPanel;

    @FXML
    private VBox FormularioPanel;

    @FXML
    private VBox ListPanel;

    //Campos texto formularios
    @FXML
    private TextField nombre;

    @FXML
    private TextField apellido;

    @FXML
    private TextField dni;

    @FXML
    private TextField edad;

    @FXML
    private TextField sexo;

    @FXML
    private DatePicker fechaNacimiento;

    @FXML
    private TextField telefono;

    @FXML
    private TextField correo;

    @FXML
    private TextField direccion;

    //Lista de personas
    
    @FXML
    private ListView<Persona> personasListView;


    @FXML
    protected void btnOnExportPersonasOnAction(){

    }

    @FXML
    protected void btnOnSavePersonaOnAction() {
        persona = Persona.builder()
                .nombre(nombre.getText())
                .apellido(apellido.getText())
                .dni(dni.getText())
                .edad(Integer.parseInt(edad.getText()))
                .sexo(sexo.getText())
                .fechaNacimiento(fechaNacimiento.getValue())
                .telefono(telefono.getText())
                .correo(correo.getText())
                .direccion(direccion.getText())
                .build();

        personas.add(persona);
        personasListView.setItems(personas); // Actualizar lista

        Module.GuardarPersFile.

        clearForm();
        selectPanelVisible(2);
    }

    @FXML
    protected void btnOnEliminarPersonaOnAction(){
        Persona seleccionada = personasListView.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            personas.remove(seleccionada);
            personasListView.setItems(personas);
            guardarPersonasEnFichero();
        }
    }





    @FXML
    protected void btnOnInsertPersonaOnAction(ActionEvent event) {
        selectPanelVisible(1);
        clearForm();

    }

    @FXML
    protected void btnBtnOnVerEliminarOnAction(ActionEvent event) {
        selectPanelVisible(2);
    }

    @FXML
    protected void btnOnExitInsertPersonaOnAction(ActionEvent event) {
        selectPanelVisible(0);
    }

    @FXML
    protected void btnOnExitLisOnAction(){
        selectPanelVisible(0);
    }

    @FXML
    protected void btnOnExitAppOnAction(ActionEvent event) {
        Platform.exit();
    }




    @FXML
    public void initialize() {
        selectPanelVisible(0);

    }







    //Selecion de los paneles
    private void selectPanelVisible(int panel){
        switch (panel) {

            case 0:
                mainPanel.setVisible(true);
                FormularioPanel.setVisible(false);
                ListPanel.setVisible(false);
                break;

            case 1:
                mainPanel.setVisible(false);
                FormularioPanel.setVisible(true);
                ListPanel.setVisible(false);
                break;

            case 2:
                mainPanel.setVisible(false);
                FormularioPanel.setVisible(false);
                ListPanel.setVisible(true);
                break;

            default:
                mainPanel.setVisible(true);
                FormularioPanel.setVisible(false);
                ListPanel.setVisible(false);

        }
    }


    private void clearForm() {
        nombre.clear();
        apellido.clear();
        dni.clear();
        edad.clear();
        sexo.clear();
        fechaNacimiento.setValue(null);
        telefono.clear();
        correo.clear();
        direccion.clear();
    }


    private boolean validarNombre(String nombre){
        return (nombre.length() > 3 && nombre.matches("[A-Z]{1}[a-z]{2,25}"));
    }

    private boolean validarTelefono(String telefono){
        return telefono.matches("[1-9]{9}");
    }

    private boolean validarCorreo(String correo){
        String correoPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return correo.matches(correoPattern);
    }

    private boolean validarEdad(String edad){
        return edad.matches("[1-9]{1,3}");
    }

    private boolean validarDNI(String dni){
        return dni.matches("[0-9]{7,8}[A-Z a-z]");
    }



}