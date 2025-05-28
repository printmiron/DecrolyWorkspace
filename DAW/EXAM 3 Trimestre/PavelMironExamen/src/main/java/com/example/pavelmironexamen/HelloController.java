package com.example.pavelmironexamen;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import Module.Mascota;
import Module.Tipo;
import Module.SqlBdAccess;
import Module.GuardarFile;
import Module.Propietario;
import Module.Consulta;

import java.util.List;


public class HelloController {

    private static SqlBdAccess BD = new SqlBdAccess();

    private Mascota mascotaEditada = null;
    private Propietario proprietarioEditado = null;

    private ObservableList<Mascota> mascotas = FXCollections.observableArrayList();
    private ObservableList<Propietario> propietarios = FXCollections.observableArrayList();
    private ObservableList<Consulta> consultas = FXCollections.observableArrayList();

    private Propietario propietario;



    //VBox`s
    @FXML
    private VBox mainPanel;

    @FXML
    private VBox registrarMascotaPanel;

    @FXML
    private VBox registrarProprietarioPanel;

    @FXML
    private VBox registrarConsultaPanel;

    @FXML
    private VBox buscarMascotaPanel;

    @FXML
    private VBox verProprietariosPanel;


    //Campos texto formulario registrar mascota
    @FXML
    private TextField idMascota;

    @FXML
    private TextField pasaporte;

    @FXML
    private TextField nombreMascota;

    @FXML
    private TextField peso;

    @FXML
    private DatePicker fechaNacimiento;

    @FXML
    private TextField proprietarioDNI;

    @FXML
    private ComboBox<Tipo> ComboBoxTipo;


    //Campos texto formulario registrar proprietario
    @FXML
    private TextField dni;

    @FXML
    private TextField nombrePropietario;

    @FXML
    private TextField apellido;

    @FXML
    private TextField telefono;

    @FXML
    private TextField direccion;

    @FXML
    private TextField email;



    //Campos texto formulario registrar consulta
    @FXML
    private DatePicker fechaConsulta;

    @FXML
    private TextField duracion;

    @FXML
    private TextField observaciones;

    @FXML
    private TextField pasaporteConsulta;

    @FXML
    private TextField dniConsulta;



    //Lista de las mascotas

    @FXML
    private ListView<Mascota> ListViewMascota;

    //Lista de las proprietarios

    @FXML
    private ListView<Propietario> ListViewProprietarios;


    @FXML
    public void initialize() {
        selectPanelVisible(0);
        ListViewMascota.setItems(mascotas);

        ListViewProprietarios.setItems(propietarios);

        //Cojer datos desde BD
        List<Mascota> desdeBD = BD.getMascotas();
        mascotas.addAll(desdeBD);

        //Para poder seleccionar el tipo de la mascota
        ComboBoxTipo.setItems(FXCollections.observableArrayList(Tipo.values()));



        //Cojer datos desde fichero
//        List<Mascotas> cargados = GuardarPersFile.readFile("Mascotas.dat");
//        if (cargados != null) {
//            mascotas.addAll(cargados);
//        }

        // Mascotas o Proprietarios

                List<Propietario> cargados = GuardarFile.readFile("Propriotarios.dat");
        if (cargados != null) {
            propietarios.addAll(cargados);
        }


    }








    //Botones para guardar mascota, propriteario, consulta
    @FXML
    public void btnOnSaveMascotaOnAction(ActionEvent event) {
        if (!validarFormularioMascota()) return;

        Tipo tipoSelecionado = ComboBoxTipo.getValue();

        if (mascotaEditada == null) {
            //Crear nueva mascota
            Mascota nueva = Mascota.builder()
                    .pasaporte(pasaporte.getText())
                    .nombre(nombreMascota.getText())
                    .fechaNacimiento(fechaNacimiento.getValue().atStartOfDay())
                    .peso(Double.valueOf(peso.getText()))
                    .tipo(tipoSelecionado)
//   no se como implementar bien esta liena  -> .propietario(proprietarioDNI.getText())
                    .build();

                BD.registrarMascota(nueva);
                mascotas.add(nueva);

        } else {

        //modificar existente
        mascotaEditada.setPasaporte(pasaporte.getText());
        mascotaEditada.setNombre(nombreMascota.getText());
        mascotaEditada.setFechaNacimiento(fechaNacimiento.getValue().atStartOfDay());
        mascotaEditada.setPeso(Double.valueOf(peso.getText()));
        mascotaEditada.setTipo(tipoSelecionado);
//     tengo complicado esta parte del coidgo ->   mascotaEditada.setPropietario();

        BD.editarMascota(mascotaEditada, pasaporteOriginal);

        }

        clearFormMascota();
        selectPanelVisible(0);

    }






    //funciona bien
    @FXML
    public void btnOnSavePropietarioOnAction(ActionEvent event) {
        if (!validarFormularioProprietario()) return;

        if (proprietarioEditado == null) {
            //Crear nueva mascota
            Propietario nuevo = Propietario.builder()
                    .dni(dni.getText())
                    .nombre(nombrePropietario.getText())
                    .apellido(apellido.getText())
                    .telefono(telefono.getText())
                    .direccion(direccion.getText())
                    .email(email.getText())
                    .build();

            BD.registrarProprietario(nuevo);
            propietarios.add(nuevo);

        } else {

            //modificar existente
            proprietarioEditado.setDni(dni.getText());
            proprietarioEditado.setNombre(nombrePropietario.getText());
            proprietarioEditado.setApellido(apellido.getText());
            proprietarioEditado.setTelefono(telefono.getText());
            proprietarioEditado.setDireccion(direccion.getText());
            proprietarioEditado.setEmail(email.getText());

            BD.editarProrietarios(proprietarioEditado, dniOriginal);
            ListViewProprietarios.refresh();
            proprietarioEditado = null;

        }

        clearFormProprietario();
        selectPanelVisible(0);

    }



    @FXML
    public void btnOnSaveConsultaOnAction(ActionEvent event){
        if (!validarFormularioConsulta()) return;

        //crear nueva consulta

        Consulta nueva = Consulta.builder()
                .id(Integer.parseInt(idMascota.getText()))
                .fechaConsulta(fechaConsulta.getValue().atStartOfDay())
                .duracion(Integer.parseInt(duracion.getText()))
                .observaciones(observaciones.getText())
//      ERROR  ->        .pasaporteConsulta(pasaporteConsulta.getText())
//       ERROR  ->      .dniConsulta(dniConsulta.getText())
                .build();



        BD.registrarConsulta(nueva);
        consultas.add(nueva);


        clearFormConsulta();
        selectPanelVisible(0);
    }














    //botones para borrar y editar mascotas

    @FXML
    public void btnOnEliminarMascotaOnAction(ActionEvent event){
        Mascota seleccionada = ListViewMascota.getSelectionModel().getSelectedItem(); //Selecionar en la lista
        if (seleccionada == null) {
            BD.borrarMascota(seleccionada.getPasaporte());
            mascotas.remove(seleccionada);
        }else {
            mostrarAlertaError("ELIMINAR!", "Tienes que seleccionar un mascota para eliminar");
        }
    }









    //esto es para editar y al selecionar lo va hacer por pasaporte
    private String pasaporteOriginal;


    @FXML
    public void btnOnEditMascotaOnAction(ActionEvent event){
        Mascota seleccionada = ListViewMascota.getSelectionModel().getSelectedItem(); //Selecionar en la lista
        if (seleccionada != null) {
            mascotaEditada = seleccionada;

            //se puede asignar todo
            pasaporteOriginal = mascotaEditada.getPasaporte();
            nombreMascota.setText(mascotaEditada.getNombre());
            peso.setText(Double.toString(mascotaEditada.getPeso()));

            selectPanelVisible(1);
        }else {
            mostrarAlertaError("Mascota", "Tienes que seleccionar un mascota");
        }
    }









    //esto es para editar y al selecionar lo va hacer por pasaporte
    private String dniOriginal;


    @FXML
    public void btnOnEditProprietariosOnAction(ActionEvent event){
        Propietario seleccionado = ListViewProprietarios.getSelectionModel().getSelectedItem();//Selecionar en la lista
        if (seleccionado != null) {
            proprietarioEditado = seleccionado;


            dniOriginal = seleccionado.getDni();
            nombrePropietario.setText(proprietarioEditado.getNombre());
            apellido.setText(proprietarioEditado.getApellido());
            telefono.setText(proprietarioEditado.getTelefono());
            direccion.setText(proprietarioEditado.getDireccion());
            email.setText(proprietarioEditado.getEmail());


            selectPanelVisible(2);

        }else{
            mostrarAlertaError("Proprietatios", "Tienes que seleccionar un proprietario");
        }
    }


















    //Entrar en los paneles reg.Mascota, reg,Proprietario, reg.Consulta, y buscar.Mascotas
    @FXML
    public void btnOnRegistrarMascotaOnAction(ActionEvent event){
        selectPanelVisible(1);
        clearFormMascota();
    }

    @FXML
    public void btnOnRegistrarProprietarioOnAction(ActionEvent event){
        selectPanelVisible(2);
        clearFormProprietario();
    }

    @FXML
    public void btnOnRegistrarConsultaOnAction(ActionEvent event){
        selectPanelVisible(3);
        clearFormConsulta();
    }

    @FXML
    public void btnOnBuscarMacotaOnAction(ActionEvent event){
        selectPanelVisible(4);
    }

    @FXML
    public void btnOnVerProprietariosOnAction(ActionEvent event){
        selectPanelVisible(5);
    }






    //Salir de los paneles reg.Mascota, reg,Proprietario, reg.Consulta, y buscar.Mascotas
    @FXML
    public void btnOnExitPaneRegistrarMascotaOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneRegistrarProprietarioOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneRegistrarConsultaOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneBuscarMascotaOnAction(ActionEvent event){
        selectPanelVisible(0);
    }

    @FXML
    public void btnOnExitPaneVerProprietariosOnAction(ActionEvent event){
        selectPanelVisible(2);
    }



    //accion del buton para exportar datos
    @FXML
    public void btnOnExportarDatosOnAction(ActionEvent event){
        try {
            GuardarFile.saveInFile("Propriotarios.dat", propietarios);

            mostrarAlertaConfirmation("INFO","Propriotarios exportado exitosamente");
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
                registrarMascotaPanel.setVisible(false);
                registrarProprietarioPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarMascotaPanel.setVisible(false);
                verProprietariosPanel.setVisible(false);
                break;

            case 1:
                mainPanel.setVisible(false);
                registrarMascotaPanel.setVisible(true);
                registrarProprietarioPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarMascotaPanel.setVisible(false);
                verProprietariosPanel.setVisible(false);
                break;

            case 2:
                mainPanel.setVisible(false);
                registrarMascotaPanel.setVisible(false);
                registrarProprietarioPanel.setVisible(true);
                registrarConsultaPanel.setVisible(false);
                buscarMascotaPanel.setVisible(false);
                verProprietariosPanel.setVisible(false);
                break;

            case 3:
                mainPanel.setVisible(false);
                registrarMascotaPanel.setVisible(false);
                registrarProprietarioPanel.setVisible(false);
                registrarConsultaPanel.setVisible(true);
                buscarMascotaPanel.setVisible(false);
                verProprietariosPanel.setVisible(false);
                break;

            case 4:
                mainPanel.setVisible(false);
                registrarMascotaPanel.setVisible(false);
                registrarProprietarioPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarMascotaPanel.setVisible(true);
                verProprietariosPanel.setVisible(false);
                break;

            case 5:
                mainPanel.setVisible(false);
                registrarMascotaPanel.setVisible(false);
                registrarProprietarioPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
                buscarMascotaPanel.setVisible(false);
                verProprietariosPanel.setVisible(true);
                break;

            default:
                mainPanel.setVisible(true);
                registrarMascotaPanel.setVisible(false);
                registrarProprietarioPanel.setVisible(false);
                registrarConsultaPanel.setVisible(false);
        }
    }




    private void clearFormMascota() {
        pasaporte.clear();
        nombreMascota.clear();
        peso.clear();
        fechaNacimiento.setValue(null);
        proprietarioDNI.clear();
        ComboBoxTipo.setValue(null);


    }

    private void clearFormProprietario() {
        dni.clear();
        nombrePropietario.clear();
        apellido.clear();
        telefono.clear();
        direccion.clear();
        email.clear();
    }

    private void clearFormConsulta() {
        fechaConsulta.setValue(null);
        duracion.clear();
        observaciones.clear();
        pasaporteConsulta.clear();
        dniConsulta.clear();
    }


    private boolean validarFormularioMascota() {
        StringBuilder errores = new StringBuilder();

        if (!validarPasaporte(pasaporte.getText())) errores.append("Pasaporte invalido, debes insertar 7 digitos y 2 letras\n");
        if (nombreMascota.getText().isEmpty()) errores.append("Nombre invalido, no puede ser vacio\n");
        if (peso.getText().isEmpty()) errores.append("Peso invalido, no puede ser vacio\n");
        if (fechaNacimiento.getValue() == null) errores.append("Fecha invalido, no puede ser vacio\n");
        if (!validarDNI(dni.getText())) errores.append("DNI invalido, 8 digitos y una letra\n");
        if (ComboBoxTipo.getValue() == null) errores.append("Tipo invalido, no puede ser vacio\n");

        if (errores.length() > 0) {
            mostrarAlertaError("Errores de validación", errores.toString());
            return false;
        }

        return true;
    }



    private boolean validarFormularioProprietario() {
        StringBuilder errores = new StringBuilder();

        if (!validarDNI(dni.getText())) errores.append("DNI invalido, 8 digitos y una letra\n");
        if (!validarNombre(nombrePropietario.getText())) errores.append("Nombre invalido, debe inicar con mayuscula y tener al menos 3 digitos\n");
        if (!validarNombre(apellido.getText())) errores.append("Apellido invalido, debe inicar con mayuscula y tener al menos 3 digitos\n");
        if (!validarTelefono(telefono.getText())) errores.append("Telefono invalido, debe incluir 9 digitos\n");
        if (direccion.getText().isEmpty()) errores.append("Direccion invalido, no puede ser vacio\n");
        if (!validarCorreo(email.getText())) errores.append("Correo invalido, ej: correo@gmail.com\n");

        if (errores.length() > 0) {
            mostrarAlertaError("Errores de validación", errores.toString());
            return false;
        }

        return true;
    }


    private boolean validarFormularioConsulta() {
        StringBuilder errores = new StringBuilder();

        if (fechaConsulta.getValue() == null) errores.append("Fecha invalido, no puede ser vacio\n");
        if (duracion.getText().isEmpty()) errores.append("Duracion invalido, no puede ser vacio\n");
        if (observaciones.getText().isEmpty()) errores.append("Observaciones invalido, no puede ser vacio\n");
        if (!validarPasaporte(pasaporte.getText())) errores.append("Pasaporte invalido, debes insertar 7 digitos y 2 letras\n");
        if (!validarDNI(dniConsulta.getText())) errores.append("DNI invalido, 8 digitos y una letra\n");

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

    private boolean validarPasaporte(String dni){
        return dni != null && dni.matches("[0-9]{7}[A-Za-z][A-Za-z]");
    }

}