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
import Module.SqlBdManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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
    private ComboBox<Propietario> ComboBoxDniProprietario;

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
    private ComboBox<Mascota> ComboBoxMascotaPassConsulta;

    @FXML
    private ComboBox<Propietario> ComboBoxProprietarioDniConsulta;



    //Lista de las mascotas

    @FXML
    private ListView<Mascota> ListViewMascota;

    //Lista de las proprietarios

    @FXML
    private ListView<Propietario> ListViewProprietarios;


    @FXML
    public void initialize() {
        selectPanelVisible(0);

        

        configurarListViewMascotas();

        List<Mascota> lista = BD.getMascotas();
        mascotas = FXCollections.observableArrayList(lista);
        ListViewMascota.setItems(mascotas);

        try (Connection connection = SqlBdManager.getConnection()) {
            // Cargar datos desde BD y llenar listas internas
            Tipo.cargarTiposDesdeBD(connection);
            Propietario.cargarPropietariosDesdeBD(connection);
            Mascota.cargarMascotasDesdeBD(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Asociar listas cargadas a ListViews
        ListViewMascota.setItems(FXCollections.observableArrayList(Mascota.getMascotas()));

        ListViewProprietarios.setItems(FXCollections.observableArrayList(Propietario.getPropietarios()));

        // Cargar tipos de mascota al ComboBox
        ComboBoxTipo.setItems(FXCollections.observableArrayList(Tipo.getTipos()));

        // Cargar propietarios al ComboBox de crear mascota
        ComboBoxDniProprietario.getItems().clear();
        ComboBoxDniProprietario.setItems(FXCollections.observableArrayList(Propietario.getPropietarios()));

        // Cargar propietarios al ComboBox de crear consulta
        ComboBoxProprietarioDniConsulta.getItems().clear();
        ComboBoxProprietarioDniConsulta.setItems(FXCollections.observableArrayList(Propietario.getPropietarios()));

        // Cargar mascotas al ComboBox
        ComboBoxMascotaPassConsulta.setItems(FXCollections.observableArrayList(Mascota.getMascotas()));





    // ---------------------------------------------------------------------------------------------
        //Cojer datos desde fichero
//        List<Mascotas> cargados = GuardarPersFile.readFile("Mascotas.dat");
//        if (cargados != null) {
//            mascotas.addAll(cargados);
//        }

        // Mascotas o Proprietarios

//        List<Propietario> cargados = GuardarFile.readFile("Propriotarios.dat");
//        if (cargados != null) {
//            propietarios.addAll(cargados);
//        }


    }








    //Botones para guardar mascota, propriteario, consulta
    @FXML
    public void btnOnSaveMascotaOnAction(ActionEvent event) {
        if (!validarFormularioMascota()) return;

        Tipo tipoSelecionado = ComboBoxTipo.getValue();
        Propietario propietarioSelecionado = ComboBoxDniProprietario.getValue();

        if (mascotaEditada == null) {
            //Crear nueva mascota
            Mascota nueva = Mascota.builder()
                    .pasaporte(pasaporte.getText())
                    .nombre(nombreMascota.getText())
                    .fechaNacimiento(fechaNacimiento.getValue().atStartOfDay())
                    .peso(Double.valueOf(peso.getText()))
                    .tipo(tipoSelecionado)
                    .propietario(propietarioSelecionado)
                    .build();

                BD.registrarMascota(nueva);
                mascotas.add(nueva);

        } else {

        //modificar existente
            mascotaEditada.setNombre(nombreMascota.getText());
            mascotaEditada.setPeso(Double.valueOf(peso.getText()));

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

        Mascota mascotaPassSelecionada = ComboBoxMascotaPassConsulta.getValue();
        Propietario propietarioDniSelecionado = ComboBoxProprietarioDniConsulta.getValue();

        Consulta nueva = Consulta.builder()
                .fechaConsulta(fechaConsulta.getValue().atStartOfDay())
                .duracion(Integer.parseInt(duracion.getText()))
                .observaciones(observaciones.getText())
                .pasaporteConsulta(mascotaPassSelecionada)
                .dniConsulta(propietarioDniSelecionado)
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
        modoEdicion = true;
        Mascota seleccionada = ListViewMascota.getSelectionModel().getSelectedItem(); //Selecionar en la lista
        if (seleccionada != null) {
            mascotaEditada = seleccionada;

            //se puede asignar todo
            pasaporteOriginal = mascotaEditada.getPasaporte();
            nombreMascota.setText(mascotaEditada.getNombre());
            peso.setText(Double.toString(mascotaEditada.getPeso()));

            pasaporte.setDisable(true);
            fechaNacimiento.setDisable(true);
            ComboBoxDniProprietario.setDisable(true);
            ComboBoxTipo.setDisable(true);

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
        modoEdicion = false;

        selectPanelVisible(1);

        pasaporte.setDisable(false);
        nombreMascota.setDisable(false);
        peso.setDisable(false);
        fechaNacimiento.setDisable(false);
        ComboBoxDniProprietario.setDisable(false);
        ComboBoxTipo.setDisable(false);

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
            GuardarFile.saveInFile("Propriotarios.dat", mascotas);

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
        ComboBoxDniProprietario.setValue(null);
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
        ComboBoxMascotaPassConsulta.setValue(null);
        ComboBoxProprietarioDniConsulta.setValue(null);
    }

    private boolean modoEdicion = false;

    private boolean validarFormularioMascota() {
        // Si estás en modo edición, no validar (o validar solo nombre y peso si querés)
        if (modoEdicion) {
            // Validar solo nombre y peso si querés
            StringBuilder errores = new StringBuilder();

            if (nombreMascota.getText().isEmpty()) errores.append("Nombre invalido, no puede ser vacio\n");
            if (peso.getText().isEmpty()) errores.append("Peso invalido, no puede ser vacio\n");

            if (errores.length() > 0) {
                mostrarAlertaError("Errores de validación", errores.toString());
                return false;
            }
            return true;
        }

        // Validación completa SOLO para registrar
        StringBuilder errores = new StringBuilder();

        if (!validarPasaporte(pasaporte.getText())) errores.append("Pasaporte invalido, debes insertar una letra y 8 digitos\n");
        if (nombreMascota.getText().isEmpty()) errores.append("Nombre invalido, no puede ser vacio\n");
        if (peso.getText().isEmpty()) errores.append("Peso invalido, no puede ser vacio\n");
        if (fechaNacimiento.getValue() == null) errores.append("Fecha invalido, no puede ser vacio\n");
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
        return dni != null && dni.matches("[A-Za-z]\\d{8}");
    }



    private void configurarListViewMascotas() {
        ListViewMascota.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Mascota mascota, boolean empty) {
                super.updateItem(mascota, empty);
                if (empty || mascota == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String texto = String.format(
                            "🐾 %s\n📜 Pasaporte: %s | 📅 Nacimiento: %s\n⚖ Peso: %.2f kg | 🧬 Tipo: %s\n👤 Propietario: %s %s",
                            mascota.getNombre(),
                            mascota.getPasaporte(),
                            mascota.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            mascota.getPeso(),
                            mascota.getTipo().getTipo(),
                            mascota.getPropietario().getNombre(),
                            mascota.getPropietario().getApellido()
                    );
                    setText(texto);
                    setStyle("-fx-padding: 10px; -fx-font-size: 13px; -fx-font-family: 'Segoe UI';");
                }
            }
        });
    }


}