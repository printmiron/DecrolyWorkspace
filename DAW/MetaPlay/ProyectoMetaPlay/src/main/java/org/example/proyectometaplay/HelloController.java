package org.example.proyectometaplay;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
//DECLARACION DE VARIABLES
    private static AccessSql miData = new AccessSql();
    private ObservableList<VideoJuego> VideojuegosFiltrados = FXCollections.observableArrayList();
    private List<VideoJuego> filtroGenero = FXCollections.observableArrayList();
    private int tipoDeFiltro = 0;
    private String seleccionFiltro;
    private Usuario usuarioAutenticado;  // Variable para almacenar el usuario de inicio de sesion

//PANELES DE LA APP
    @FXML
    private VBox VBox_MenuPrincipal;

    @FXML
    private VBox VBox_InicioSesion;

    @FXML
    private VBox Vbox_Registro;

    @FXML
    private VBox Vbox_MenuDeUsuario;

    @FXML
    private VBox Vbox_MejorValorados;

    @FXML
    private VBox VboxFiltrarJuego;

    @FXML
    private VBox Vbox_BusquedaFiltrada;

    @FXML
    private VBox AddValoracion;

    @FXML
    private VBox MisValoraciones;

//INICIO DE SESION
    @FXML
    private TextField TextField_InicioSesionUsuario;
    @FXML
    private PasswordField TextField_InicioSesionContraseña;

//VENTANA DE REGISTRO
    @FXML
    private TextField TextField_RegistroNombre;
    @FXML
    private TextField TextField_RegistroApellidos;
    @FXML
    private TextField TextField_RegistroUsuario;
    @FXML
    private TextField TextField_RegistroContraseña;
    @FXML
    private TextField TextField_RegistroCorreo;
    @FXML
    private TextField TextField_RegistroNacimiento;

//VENTANA MEJOR VALORADOS
    @FXML
    private ListView<VideoJuego> ListView_MejorValorados;

//VENTANA FILTRO
    @FXML
    private ChoiceBox<String> SeleccionFiltro;

//VENTANA BUSQUEDA FILTRADA
    @FXML
    private ListView<VideoJuego> ListView_JuegosFiltrados;

    //Panel add Valoracion
    @FXML
    private TextField puntuacionVa;
    @FXML
    private TextArea ComentarioValo;
    @FXML
    private ListView<VideoJuego> ListViewVidValo;
    @FXML
    private Button SaveValoracion;

    //Panel Menu Usuario
    @FXML
    private Button btn_NuevaValoracion;
    @FXML
    private Button btn_VerValoraciones;
    @FXML
    private Button btnAddVideoJuego;

    //Panel Valoraciones
    @FXML
    private ListView<Valoracion_Usuario> ListView_ValoracionesUsuario;
//ACCIONES DE BOTONES
    //Navegacion entre paneles
    @FXML
    protected void onInicioButton(ActionEvent event){//Boton comun para volver al menu principal
        //Ir al panel inicial
        if(usuarioAutenticado == null) {
            selectPanelVisible(0);
        }else {
            selectPanelVisible(3);
        }
        limpiarFormulario();
        cleanLogIn();
        SeleccionFiltro.getItems().clear();
        ListView_ValoracionesUsuario.setItems(null);
    }

    @FXML
    protected void onBtn_CerrarSesion(ActionEvent event){//Boton comun para volver al menu principal
        //Ir al panel inicial
        if(usuarioAutenticado != null) {
            usuarioAutenticado = null;
        }
        selectPanelVisible(0);
        limpiarFormulario();
        cleanLogIn();
        SeleccionFiltro.getItems().clear();
        ListView_ValoracionesUsuario.setItems(null);
    }

    @FXML
    protected void onEntrarMenuFiltrarConsolaButton(ActionEvent event){
        selectPanelVisible(5);
        FiltroConsola();
        tipoDeFiltro = 0;

    }

    @FXML
    protected void onEntrarMenuFiltrarGeneroButton(ActionEvent event){
        selectPanelVisible(5);
        FiltroGenero();
        tipoDeFiltro = 1;
    }

    //Boton de filtrar
    @FXML
    protected void onFiltrarButton(ActionEvent event){
        selectPanelVisible(6);
        seleccionFiltro = SeleccionFiltro.getValue();

        // Verificar que se haya seleccionado algo válido
        if (seleccionFiltro == null ||
                seleccionFiltro.equals("Seleccione Consola") ||
                seleccionFiltro.equals("Seleccione Genero")) {

            // Mostrar alerta si no se seleccionó nada válido
            showAlert(Alert.AlertType.WARNING, "Advertencia", "Por favor seleccione una opción válida");
            return;
        }

        if (tipoDeFiltro == 0){ // Filtro por consola
            ListView_JuegosFiltrados.setItems(FXCollections.observableArrayList(
                    miData.getVideoJuegosConsola(seleccionFiltro)));
        } else if (tipoDeFiltro == 1){ // Filtro por género
            try {
                // Convertir el String a GeneroV usando el método fromString
                GeneroV genero = GeneroV.fromString(seleccionFiltro);
                ListView_JuegosFiltrados.setItems(FXCollections.observableArrayList(
                        miData.getVideoJuegosGenero(genero)));
            } catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Género no válido: " + seleccionFiltro);
            }
        }
        SeleccionFiltro.getItems().clear();
    }

    @FXML
    protected void onVerValoraciones(ActionEvent event){
        selectPanelVisible(8);
        cargarValoracionesUsuario();
    }

    private void cargarValoracionesUsuario() {
        if (usuarioAutenticado != null) {
            List<Valoracion_Usuario> valoraciones = miData.getValoracionesPorUsuario(usuarioAutenticado.getId());
            ListView_ValoracionesUsuario.setItems(FXCollections.observableArrayList(valoraciones));

            // Configurar el formato de celdas para mostrar las valoraciones de forma legible
            ListView_ValoracionesUsuario.setCellFactory(listView -> new ListCell<Valoracion_Usuario>() {
                @Override
                protected void updateItem(Valoracion_Usuario valoracion, boolean empty) {
                    super.updateItem(valoracion, empty);
                    if (empty || valoracion == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Obtener el nombre del videojuego para mostrarlo
                        String nombreJuego = obtenerNombreVideojuego(valoracion.getIdVideojuego());

                        String texto = String.format(
                                "Juego: %s | Puntuación: %d/100 | Comentario: %s | Fecha: %s",
                                nombreJuego,
                                valoracion.getPuntuacion(),
                                valoracion.getComentario(),
                                valoracion.getFechaValoracion().toString()
                        );
                        setText(texto);
                    }
                }
            });
        }
    }

    private String obtenerNombreVideojuego(int idVideojuego) {
        List<VideoJuego> videojuegos = miData.getVideojuegos();
        for (VideoJuego juego : videojuegos) {
            if (juego.getId() == idVideojuego) {
                return juego.getNombre();
            }
        }
        return "Juego no encontrado";
    }

//METODO INICIALIZADOR
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    selectPanelVisible(0);

    //Validaciones de los campos de texto
    TextField_RegistroNombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if(!newValue){
            if(!validateName(TextField_RegistroNombre.getText())){
                TextField_RegistroNombre.setText("");
                TextField_RegistroNombre.setPromptText("Valor incorrecto");
            }
        }
    });


    TextField_RegistroCorreo.focusedProperty().addListener((observable, oldValue, newValue) -> {
        if(!newValue){
            if(!validateEmail(TextField_RegistroCorreo.getText())){
                TextField_RegistroCorreo.setText("");
                TextField_RegistroCorreo.setPromptText("Valor incorrecto");
            }
        }
    });

    TextField_RegistroNacimiento.setPromptText("dd/MM/yyyy");

    //Le damos un formato de celdas al listview Para que salga bien los mejores valorados
        ListView_MejorValorados.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(VideoJuego videojuego, boolean empty) {
                super.updateItem(videojuego, empty);
                if (empty || videojuego == null) {
                    setText(null);
                    setGraphic(null); // Limpiar gráficos si los hay
                } else {
                    setText(String.format("ID: %d | Nombre: %s | Puntuación Global: %.2f",
                            videojuego.getId(),
                            videojuego.getNombre(),
                            videojuego.getPuntuacionGlobal()));
                }
            }
        });

        //Le damos un formato de celdas al listview Para que salga bien con las valoraciones
        ListView_JuegosFiltrados.setCellFactory(lv -> new ListCell<VideoJuego>() {
            @Override
            protected void updateItem(VideoJuego videojuego, boolean empty) {
                super.updateItem(videojuego, empty);
                if (empty || videojuego == null) {
                    setText(null);
                    setGraphic(null); // Limpiar gráficos si los hay
                } else {
                    // Formatear el texto con los datos del videojuego
                    String texto = String.format(
                            "ID: %d | Nombre: %s | Género: %s | Consola: %s | Precio: %.2f | Desarrollador: %s | Puntuación Global: %.2f",
                            videojuego.getId(),
                            videojuego.getNombre(),
                            videojuego.getGenero(),
                            videojuego.getNombre_Consola(),
                            videojuego.getPrecio(),
                            videojuego.getDesarrollador(),
                            videojuego.getPuntuacionGlobal()
                    );
                    setText(texto);
                }
            }
        });

    //Listview para los mejores valorados
        try {
            ListView_MejorValorados.setItems(FXCollections.observableList(miData.obtenerMejoresVideojuegos()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//FIN DE METODO INICIALIZADOR

//VALIDACIONES DE LOS CAMPOS DE TEXTO
    private boolean validateName(String name){
        return (name.length() > 3 && name.matches("[A-Z]{1}[a-z]{2,25}"));
    }

    private boolean validateEmail(String email){
        String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(emailPattern);
    }

    private boolean validateCif(String cif) {
        return cif.matches("[A-Z]{1}[0-9]{8}");
    }

    @FXML
    protected void onBtn_IniciarSesion() {
        selectPanelVisible(1);
        cleanLogIn();
    }
    @FXML
    protected void onBtn_Registrarse() {
    selectPanelVisible(2);
    limpiarFormulario();
    }



    //Registrar Usuario
    @FXML
    protected void onBtn_RegistrarUsuario() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento = LocalDate.parse(TextField_RegistroNacimiento.getText(), formatter);
        Usuario newUsuario = new Usuario(
                TextField_RegistroNombre.getText(),
                TextField_RegistroApellidos.getText(),
                TextField_RegistroUsuario.getText(),
                TextField_RegistroContraseña.getText(),
                TextField_RegistroCorreo.getText(),
                fechaNacimiento

                );
        miData.registrarUsuario(newUsuario);
        selectPanelVisible(1);
    }

    @FXML
    protected void onBtn_Acceder() {
        String usuarioInt = TextField_InicioSesionUsuario.getText();
        String password = TextField_InicioSesionContraseña.getText();

        // Obtener la lista de usuarios de la base de datos
        List <Usuario> usuarios = miData.getUsuarios();


        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(usuarioInt) &&
                    usuario.getContrasena().equals(password)) {
                this.usuarioAutenticado = usuario;  // Almacena el usuario
                selectPanelVisible(3);
                return; //Sale del metodo al encontrar una coincidencia
            }
        }
        // Si llega aquí, las credenciales son inválidas
        showAlert(Alert.AlertType.ERROR, "Error", "Usuario o contraseña incorrectos");
        cleanLogIn();

    }
    //Mostrar alertas
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void FiltroConsola() {
        SeleccionFiltro.getItems().addAll("PlayStation 5", "Xbox Series X", "Nintendo Switch", "PC", "PlayStation 4");
        SeleccionFiltro.setValue("Seleccione Consola");

        // Listener para detectar cambios
        SeleccionFiltro.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Seleccionaste: " + newVal);
        });
    }

    private void FiltroGenero() {
        SeleccionFiltro.getItems().addAll("ACCION", "AVENTURA", "CATASTROFE", "CIENCIA_FICCION",
        "COMEDIA", "DOCUMENTALES", "DRAMA", "FANTASIA");
        SeleccionFiltro.setValue("Seleccione Genero");

        // Listener para detectar cambios
        SeleccionFiltro.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Seleccionaste: " + newVal);
        });
    }

    //Botones del menu de usuario
    @FXML
    private void onBtn_MostrarJuegos(){
        selectPanelVisible(4);
        try {
            ListView_MejorValorados.setItems(FXCollections.observableList(miData.obtenerMejoresVideojuegos()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onbtn_NuevaValoracion(){
        selectPanelVisible(7);
        ListViewVidValo.setItems(FXCollections.observableArrayList(miData.getVideojuegos()));
        puntuacionVa.setPromptText("1-100");
    }

    //Boton guardar nueva consulta
    @FXML
    private void onSaveValoracion(){
        VideoJuego selectV = ListViewVidValo.getSelectionModel().getSelectedItem();
        // Convertir el texto de puntuación a int
        int puntuacion = Integer.parseInt(puntuacionVa.getText());
        Valoracion_Usuario valoracion = new Valoracion_Usuario(
            selectV.getId(),
            usuarioAutenticado.getId(),
            puntuacion,
            ComentarioValo.getText()
        );
        miData.valoracionUsuario(valoracion);
        selectPanelVisible(3);
        cleanValoracion();
    }

    //METODOS DE LIMPIEZA DE FORMULARIO
    private void limpiarFormulario() {
        TextField_RegistroNombre.setText("");
        TextField_RegistroApellidos.setText("");
        TextField_RegistroUsuario.setText("");
        TextField_RegistroContraseña.setText("");
        TextField_RegistroCorreo.setText("");
        TextField_RegistroNacimiento.setText("");
    }
    //Metodo para limpiar iniciar sesion
    private void cleanLogIn(){
        TextField_InicioSesionUsuario.setText("");
        TextField_InicioSesionContraseña.setText("");
    }
    private void cleanValoracion(){
        puntuacionVa.setText("");
        ComentarioValo.setText("");
    }

    //METODO PARA SELECCION DE PANELES
    private void selectPanelVisible(int panel){
        switch (panel) {

            case 0:
                VBox_MenuPrincipal.setVisible(true);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
                break;

            case 1:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(true);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
                break;

            case 2:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(true);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
                break;
            case 3:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(true);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
                break;
            case 4:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(true);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
                break;
            case 5:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(true);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
                break;
            case 6:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(true);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
                break;
            case 7:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(true);
                MisValoraciones.setVisible(false);
                break;

            case 8:
                VBox_MenuPrincipal.setVisible(false);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(true);
                break;

            default:
                VBox_MenuPrincipal.setVisible(true);
                VBox_InicioSesion.setVisible(false);
                Vbox_Registro.setVisible(false);
                Vbox_MenuDeUsuario.setVisible(false);
                Vbox_MejorValorados.setVisible(false);
                VboxFiltrarJuego.setVisible(false);
                Vbox_BusquedaFiltrada.setVisible(false);
                AddValoracion.setVisible(false);
                MisValoraciones.setVisible(false);
        }
    }

    public static GeneroV convertirTextoAGenero(String texto) {
        switch (texto) {
            case "ACCION":
                return GeneroV.ACCION;
            case "AVENTURA":
                return GeneroV.AVENTURA;
            case "CATASTROFE":
                return GeneroV.CATASTROFE;
            case "CIENCIA_FICCION":
                return GeneroV.CIENCIA_FICCION;
            case "COMEDIA":
                return GeneroV.COMEDIA;
            case "DOCUMENTALES":
                return GeneroV.DOCUMENTALES;
            case "DRAMA":
                return GeneroV.DRAMA;
            case "FANTASIA":
                return GeneroV.FANTASIA;
            default:
                return null;
        }
    }
}//