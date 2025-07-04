package com.example.simulacroexam;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import Module.SqlBdAccess;
import Module.Persona;
import Module.Sexo;
import Module.GuardarPersFile;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.util.List;


public class HelloController {

    private static SqlBdAccess BD = new SqlBdAccess();

    private Persona personaEditada = null;
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
    private ComboBox <Sexo> ComboBoxSexo;

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
    private ListView<Persona> ListViewPersonas;




    @FXML
    public void initialize() {
        selectPanelVisible(0);
        ListViewPersonas.setItems(personas);

        //Cojer datos desde BD
        List<Persona> desdeBD = BD.getPersonas();
        personas.addAll(desdeBD);

        //Para poder seleccionar M o F
        ComboBoxSexo.setItems(FXCollections.observableArrayList(Sexo.values()));



        //Cojer poersoans desde fichero
//        List<Persona> cargados = GuardarPersFile.readFile("Personas.dat");
//        if (cargados != null) {
//            personas.addAll(cargados);
//        }


    }




    @FXML
    protected void btnOnExportPersonasOnAction(){
        try {
            GuardarPersFile.saveInFile("Personas.dat", personas);

            mostrarAlertaConfirmation("INFO","Personas exportado exitosamente");
        } catch (Exception e) {
            mostrarAlertaConfirmation("ERROR",e.getMessage());
        }
    }





    @FXML
    protected void btnOnSavePersonaOnAction() {
        if (!validarFormulario()) return;

        Sexo sexoSeleccionado = ComboBoxSexo.getValue();


        if (personaEditada == null) {
              //Crear nueva
            Persona nueva = Persona.builder()
                    .nombre(nombre.getText())
                    .apellido(apellido.getText())
                    .dni(dni.getText())
                    .edad(Integer.parseInt(edad.getText()))
                    .sexo(sexoSeleccionado)
                    .fechaNacimiento(fechaNacimiento.getValue())
                    .telefono(telefono.getText())
                    .correo(correo.getText())
                    .direccion(direccion.getText())
                    .build();

              BD.registrarPersona(nueva);
              personas.add(nueva);

          }else {

              //Modificar existente
              personaEditada.setNombre(nombre.getText());
              personaEditada.setApellido(apellido.getText());
              personaEditada.setDni(dni.getText());
              personaEditada.setEdad(Integer.parseInt(edad.getText()));
              personaEditada.setSexo(ComboBoxSexo.getValue());
              personaEditada.setFechaNacimiento(fechaNacimiento.getValue());
              personaEditada.setTelefono(telefono.getText());
              personaEditada.setCorreo(correo.getText());
              personaEditada.setDireccion(direccion.getText());

              BD.editarPersona(personaEditada, dniOriginal);
              ListViewPersonas.refresh();
              personaEditada = null;
          }



          clearForm();
          selectPanelVisible(0);



    }

    @FXML
    protected void btnOnEliminarPersonaOnAction(){
        Persona seleccionada = ListViewPersonas.getSelectionModel().getSelectedItem(); //Selecionar en la lista
        if (seleccionada != null) {
            BD.eliminarPersona(seleccionada.getDni());
            personas.remove(seleccionada);
        }else {
            mostrarAlertaError("Eliminar", "Tienes que seleccionar un persona para eliminar");
        }
    }

    private String dniOriginal;

    @FXML
    protected void btnOnEditPersonaOnAction(ActionEvent event) {
        Persona seleccionada = ListViewPersonas.getSelectionModel().getSelectedItem(); //Selecionar en la lista
        if (seleccionada != null) {
            personaEditada = seleccionada;

            dniOriginal = seleccionada.getDni();
            nombre.setText(personaEditada.getNombre());
            apellido.setText(personaEditada.getApellido());
            dni.setText(personaEditada.getDni());
            edad.setText(String.valueOf(personaEditada.getEdad()));
            ComboBoxSexo.setValue(personaEditada.getSexo());
            fechaNacimiento.setValue(personaEditada.getFechaNacimiento());
            telefono.setText(personaEditada.getTelefono());
            correo.setText(personaEditada.getCorreo());
            direccion.setText(personaEditada.getDireccion());

            selectPanelVisible(1);
        }else {
            mostrarAlertaError("Edit", "Debes elecionar una persona antes de editar");
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
        ComboBoxSexo.setValue(null);
        fechaNacimiento.setValue(null);
        telefono.clear();
        correo.clear();
        direccion.clear();
        personaEditada = null;
    }



    private boolean validarFormulario() {
        StringBuilder errores = new StringBuilder();

        if (!validarNombre(nombre.getText())) errores.append("Nombre inválido. Debe iniciar en mayúscula y tener mínimo 3 letras.\n");
        if (!validarNombre(apellido.getText())) errores.append("Apellido inválido. Debe iniciar en mayúscula y tener mínimo 3 letras.\n");
        if (!validarDNI(dni.getText())) errores.append("DNI inválido. Ej: 12345678A\n");
        if (!validarEdad(edad.getText())) errores.append("Edad inválida. Debe ser un número entre 1 y 120.\n");
        if (ComboBoxSexo.getValue() == null) errores.append("Sexo no puede estar vacío.\n");
        if (fechaNacimiento.getValue() == null) errores.append("Debe seleccionar una fecha de nacimiento.\n");
        if (!validarTelefono(telefono.getText())) errores.append("Teléfono inválido. Debe contener 9 dígitos.\n");
        if (!validarCorreo(correo.getText())) errores.append("Correo electrónico inválido.\n");
        if (direccion.getText().isEmpty()) errores.append("Dirección no puede estar vacía.\n");

        if (errores.length() > 0) {
            mostrarAlertaError("Errores de validación", errores.toString());
            return false;
        }

        return true;
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaConfirmation(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private boolean validarNombre(String nombre){
        return nombre != null && nombre.matches("[A-ZÁÉÍÓÚÑ][a-záéíóúñ]{2,25}");
    }

    private boolean validarTelefono(String telefono){
        return telefono != null && telefono.matches("[0-9]{9}");
    }

    private boolean validarCorreo(String correo){
        String correoPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return correo != null && correo.matches(correoPattern);
    }

    private boolean validarEdad(String edad){
        return edad != null && edad.matches("\\d{1,3}") && Integer.parseInt(edad) > 0 && Integer.parseInt(edad) < 120;
    }

    private boolean validarDNI(String dni){
        return dni != null && dni.matches("[0-9]{7,8}[A-Za-z]");
    }




}