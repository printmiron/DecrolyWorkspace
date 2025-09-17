package com.example.examrecuperacion;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import Module.SqlBdAccess;
import Module.Paciente;
import Module.Consulta;
import Module.Persona;
import Module.Doctor;
import Module.Tipo;
import Module.SqlBdManager;
import Module.GuardarFile;

import javafx.scene.layout.VBox;


import java.sql.Connection;
import java.sql.SQLException;

public class HelloController {

    private static SqlBdAccess BD = new SqlBdAccess();

    private ObservableList<Paciente> pacientes = FXCollections.observableArrayList();
    private ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    private ObservableList<Consulta> consultas = FXCollections.observableArrayList();
    private ObservableList<Tipo> tipos = FXCollections.observableArrayList();


    //VBox`s
    @FXML
    private VBox mainPanel;

    @FXML
    private VBox registrarPacientePanel;

    @FXML
    private VBox registrarDoctorPanel;

    @FXML
    private VBox registrarConsultaPanel;

    @FXML
    private VBox buscarConsultaPanel;

    @FXML
    private VBox verDoctoresPanel;


    //Campos texto formulario registrar paciente
    @FXML
    private TextField nombrePaciente;

    @FXML
    private TextField apellidoPaciente;

    @FXML
    private TextField dniPaciente;

    @FXML
    private TextField telefonoPaciente;

    @FXML
    private TextField direccionPaciente;

    @FXML
    private TextField emailPaciente;


    //Campos texto formulario registrar doctor
    @FXML
    private TextField dniDoctor;

    @FXML
    private TextField nombreDoctor;

    @FXML
    private TextField apellidoDoctor;

    @FXML
    private TextField telefonoDoctor;

    @FXML
    private TextField direccionDoctor;

    @FXML
    private TextField emailDoctor;

    @FXML
    private ComboBox<Tipo> ComboBoxTipoDoctor;



    //Campos texto formulario registrar consulta
    @FXML
    private TextField observaciones;

    @FXML
    private DatePicker fechaConsulta;

    @FXML
    private ComboBox<Tipo> ComboBoxTipoConsulta;

    @FXML
    private ComboBox<Paciente> ComboBoxDniPaciente;

    @FXML
    private ComboBox<Doctor> ComboBoxDniDoctor;

    //buscar cosnultas
    @FXML
    private ChoiceBox<String> ChoiceBoxFiltroConsulta;

    @FXML
    private ListView ListViewDoctores;

    @FXML
    public void initialize() {
        selectPanelVisible(0);


        // Inicializa las listas observables
        pacientes = FXCollections.observableArrayList();
        doctors = FXCollections.observableArrayList();
        tipos = FXCollections.observableArrayList();

        ListViewDoctores.setItems(doctors);
        ComboBoxTipoDoctor.setItems(tipos);
        ComboBoxDniPaciente.setItems(pacientes);
        ComboBoxDniDoctor.setItems(doctors);
        ComboBoxTipoConsulta.setItems(tipos);

        try (Connection connection = SqlBdManager.getConnection()) {
            Tipo.cargarTiposDesdeBD(connection);
            tipos.setAll(Tipo.getTipos());

            Paciente.cargarPacientesDesdeBD(connection);
            pacientes.setAll(Paciente.getPacientes());

            Doctor.cargarDoctoresDesdeBD(connection);
            doctors.setAll(Doctor.getDoctors());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public  void btnOnExitPanelFiltroPacienteOnAction(ActionEvent event) {

    }

    @FXML
    public void btnOnSavePacienteOnAction(ActionEvent event){
        if (!validarFormularioPaciente()) return;

        if (BD.existeDniPaciente(dniPaciente.getText())) {
            mostrarAlertaError("Error DNI", "DNI paciente ya existe");
            return;
        }

        //crear nueva consulta
        Paciente nuevo = (Paciente) Paciente.builder()
                .Dni(dniPaciente.getText())
                .Nombre(nombrePaciente.getText())
                .Apellido(apellidoPaciente.getText())
                .Telefono(telefonoPaciente.getText())
                .Direccion(direccionPaciente.getText())
                .Correo(emailPaciente.getText())
                .build();



        BD.registrarPaciente(nuevo);
        pacientes.add(nuevo);


        clearFormPaciente();
        selectPanelVisible(0);
    }









    @FXML
    public void btnOnSaveDoctorOnAction(ActionEvent event){
        if (!validarFormularioDoctor()) return;

        if (BD.existeNccDoctor(dniDoctor.getText())) {
            mostrarAlertaError("Error DNI", "DNI doctor ya existe");
            return;
        }

        Tipo tipoSelecionado = ComboBoxTipoConsulta.getValue();

        //crear nueva consulta
        Doctor nuevo = (Doctor) Doctor.builder()
                .Dni(dniPaciente.getText())
                .Nombre(nombrePaciente.getText())
                .Apellido(apellidoPaciente.getText())
                .Telefono(telefonoPaciente.getText())
                .Direccion(direccionPaciente.getText())
                .Correo(emailPaciente.getText())
                .Tipo(tipoSelecionado)
                .build();



        BD.registrarDoctor(nuevo);
        ListViewDoctores.refresh();
        doctors.add(nuevo);


        clearFormPaciente();
        selectPanelVisible(0);
    }





    @FXML
    public void btnOnSaveConsultaOnAction(ActionEvent event){
        if (!validarFormularioConsulta()) return;

        //crear nueva consulta

        Tipo tipoSelecionado = ComboBoxTipoConsulta.getValue();
        Paciente pacienteSelecionado = ComboBoxDniPaciente.getValue();
        Doctor doctorSelecionado = ComboBoxDniDoctor.getValue();

        Consulta nueva = Consulta.builder()
                .Observaciones(observaciones.getText())
                .Fecha(fechaConsulta.getValue().atStartOfDay())
                .Tipo(tipoSelecionado)
                .Paciente(pacienteSelecionado)
                .Doctor(doctorSelecionado)
                .build();



        BD.registrarConsulta(nueva);
        consultas.add(nueva);


        clearFormConsulta();
        selectPanelVisible(0);
    }





    //entrar paneles
    @FXML
    public void btnOnRegistrarPacienteOnAction(ActionEvent event){
        selectPanelVisible(1);
        clearFormPaciente();
    }

    @FXML
    public void btnOnRegistrarDoctorOnAction(ActionEvent event){
        selectPanelVisible(2);
        clearFormDoctor();
    }

    @FXML
    public void btnOnRegistrarConsultaOnAction(ActionEvent event){
        selectPanelVisible(3);
        clearFormConsulta();
    }

    @FXML
    public void btnOnBuscarConsultaOnAction(ActionEvent event){
        selectPanelVisible(4);
    }

    @FXML
    public void btnOnVerDoctorOnAction(ActionEvent event){
        selectPanelVisible(5);
    }





    //salir paneles

    @FXML
    public void btnOnExitPaneRegistrarPacienteOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneRegistrarDoctorOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneRegistrarConsultaOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneBuscarConsultaOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneVerDoctoresOnAction(ActionEvent event){
        selectPanelVisible(2);
    }




    //accion del buton para exportar datos
    @FXML
    public void btnOnExportarDatosOnAction(ActionEvent event){
        try {
            GuardarFile.saveInFile("Consultas.dat", consultas);

            mostrarAlertaConfirmation("INFO","Consultas exportado exitosamente");
        } catch (Exception e) {
            mostrarAlertaConfirmation("ERROR",e.getMessage());
        }
    }


    //salir de app
    @FXML
    public void btnOnExitAppOnAction(ActionEvent event){
        Platform.exit();
    }


    //Selecion de los paneles
    private void selectPanelVisible(int panel){
        switch (panel) {

            case 0:
                mainPanel.setVisible(true);
                registrarPacientePanel.setVisible(false);
                registrarDoctorPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarConsultaPanel.setVisible(false);
                verDoctoresPanel.setVisible(false);
                break;

            case 1:
                mainPanel.setVisible(false);
                registrarPacientePanel.setVisible(true);
                registrarDoctorPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarConsultaPanel.setVisible(false);
                verDoctoresPanel.setVisible(false);
                break;

            case 2:
                mainPanel.setVisible(false);
                registrarPacientePanel.setVisible(false);
                registrarDoctorPanel.setVisible(true);
                registrarConsultaPanel.setVisible(false);
                buscarConsultaPanel.setVisible(false);
                verDoctoresPanel.setVisible(false);
                break;

            case 3:
                mainPanel.setVisible(false);
                registrarPacientePanel.setVisible(false);
                registrarDoctorPanel.setVisible(false);
                registrarConsultaPanel.setVisible(true);
                buscarConsultaPanel.setVisible(false);
                verDoctoresPanel.setVisible(false);
                break;

            case 4:
                mainPanel.setVisible(false);
                registrarPacientePanel.setVisible(false);
                registrarDoctorPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarConsultaPanel.setVisible(true);
                verDoctoresPanel.setVisible(false);
                break;

            case 5:
                mainPanel.setVisible(false);
                registrarPacientePanel.setVisible(false);
                registrarDoctorPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarConsultaPanel.setVisible(false);
                verDoctoresPanel.setVisible(true);
                break;

            default:
                mainPanel.setVisible(true);
                registrarPacientePanel.setVisible(false);
                registrarDoctorPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarConsultaPanel.setVisible(false);
                verDoctoresPanel.setVisible(false);
        }
    }


    private void clearFormPaciente() {
        nombrePaciente.clear();
        apellidoPaciente.clear();
        dniPaciente.clear();
        telefonoPaciente.clear();
        direccionPaciente.clear();
        emailPaciente.clear();
    }

    private void clearFormDoctor() {
        nombreDoctor.clear();
        apellidoDoctor.clear();
        dniDoctor.clear();
        telefonoDoctor.clear();
        direccionDoctor.clear();
        emailDoctor.clear();
    }

    private void clearFormConsulta() {
       observaciones.clear();
       fechaConsulta.setValue(null);
       ComboBoxTipoConsulta.setValue(null);
       ComboBoxDniPaciente.setValue(null);
       ComboBoxDniDoctor.setValue(null);
    }


    private boolean validarFormularioDoctor() {
        StringBuilder errores = new StringBuilder();

        if (!validarNombre(nombreDoctor.getText())) errores.append("Nombre invalido, debe inicar con mayuscula y tener al menos 3 digitos\n");
        if (!validarNombre(apellidoDoctor.getText())) errores.append("Apellido invalido, debe inicar con mayuscula y tener al menos 3 digitos\n");
        if (!validarDNI(dniDoctor.getText())) errores.append("DNI invalido, 8 digitos y una letra\n");
        if (!validarTelefono(telefonoDoctor.getText())) errores.append("Telefono invalido, debe incluir 9 digitos\n");
        if (direccionDoctor.getText().isEmpty()) errores.append("Direccion invalido, no puede ser vacio\n");
        if (!validarCorreo(emailDoctor.getText())) errores.append("Correo invalido, ej: correo@gmail.com\n");
        if (ComboBoxTipoDoctor.getValue() == null) errores.append("Tipo invalido, no puede ser vacio\n");

        if (errores.length() > 0) {
            mostrarAlertaError("Errores de validación", errores.toString());
            return false;
        }

        return true;
    }


    private boolean validarFormularioPaciente() {
        StringBuilder errores = new StringBuilder();

        if (!validarNombre(nombrePaciente.getText())) errores.append("Nombre invalido, debe inicar con mayuscula y tener al menos 3 digitos\n");
        if (!validarNombre(apellidoPaciente.getText())) errores.append("Apellido invalido, debe inicar con mayuscula y tener al menos 3 digitos\n");
        if (!validarDNI(dniPaciente.getText())) errores.append("DNI invalido, 8 digitos y una letra\n");
        if (!validarTelefono(telefonoPaciente.getText())) errores.append("Telefono invalido, debe incluir 9 digitos\n");
        if (direccionPaciente.getText().isEmpty()) errores.append("Direccion invalido, no puede ser vacio\n");
        if (!validarCorreo(emailPaciente.getText())) errores.append("Correo invalido, ej: correo@gmail.com\n");

        if (errores.length() > 0) {
            mostrarAlertaError("Errores de validación", errores.toString());
            return false;
        }

        return true;
    }

    private boolean validarFormularioConsulta() {
        StringBuilder errores = new StringBuilder();

        if (observaciones.getText().isEmpty()) errores.append("Observaciones invalido, no puede ser vacio\n");
        if (fechaConsulta.getValue() == null) errores.append("Fecha consulta invalido, no puede ser vacio\n");
        if (ComboBoxTipoConsulta.getValue() == null) errores.append("Tipo invalido, no puede ser vacio\n");
        if (ComboBoxDniPaciente.getValue() == null) errores.append("Paciente invalido, no puede ser vacio\n");
        if (ComboBoxDniDoctor.getValue() == null) errores.append("Doctor invalido, no puede ser vacio\n");

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

    private boolean validarDNI(String dni){
        return dni != null && dni.matches("[0-9]{7,8}[A-Za-z]");
    }


}