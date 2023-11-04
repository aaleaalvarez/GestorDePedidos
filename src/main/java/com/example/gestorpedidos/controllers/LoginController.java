package com.example.gestorpedidos.controllers;

import com.example.gestorpedidos.Session;
import com.example.gestorpedidos.domain.DBConnection;
import com.example.gestorpedidos.domain.Usuario.Usuario;
import com.example.gestorpedidos.domain.Usuario.UsuarioDAO;
import com.example.gestorpedidos.domain.Usuario.UsuarioDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador encargado de la lógica de la vista de inicio de sesión.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public class LoginController implements Initializable {
    /**
     * Objeto para acceder a la base de datos de usuarios.
     */
    private UsuarioDAO usuarioDAO;
    /**
     * Usuario actual que ha iniciado sesión.
     */
    private Usuario currentUser;

    /**
     * Elementos de la interfaz de usuario definidos con anotaciones @FXML.
     */
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button entrarButton;
    @FXML
    private Button cancelarButton;

    /**
     * Constructor por defecto del controlador.
     */
    public LoginController() {
    }

    /**
     * Método de inicialización del controlador.
     *
     * @param url            URL de la ubicación del FXML a inicializar.
     * @param resourceBundle Recursos específicos del local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar usuarioDAO aquí
        Connection connection = DBConnection.getConnection();
        this.usuarioDAO = new UsuarioDAOImp(connection);
    }

    /**
     * Maneja el evento de inicio de sesión cuando se hace clic en el botón de entrada.
     *
     * @param actionEvent Evento del botón de inicio de sesión.
     * @throws IOException Excepción lanzada si hay un error al cargar la siguiente vista.
     */
    @FXML
    public void login(ActionEvent actionEvent) throws IOException {
        String usuario = userField.getText();
        String contraseña = passField.getText();

        List<Usuario> usuarios = usuarioDAO.findAll();
        Usuario user = null;
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(usuario) && u.getContraseña().equals(contraseña)) {
                user = u;
                currentUser = u; // Almacenar el usuario actual
                break;
            }
        }

        if (user != null) {
            // Guarda el ID y el nombre del usuario en Session
            Session.setUsuarioLogeado(user.getNombre());
            Session.setUsuarioId(user.getId());

            // Cierra la ventana actual
            Stage stageActual = (Stage) userField.getScene().getWindow();
            stageActual.close();

            // Abre VentanaPrincipal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpedidos/listPedidos-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ventana Principal");
            stage.show();

        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Login");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos.");
            alert.showAndWait();
        }
    }

    /**
     * Cierra la aplicación cuando se hace clic en el botón de cierre.
     *
     * @param actionEvent Evento del botón de cierre de la aplicación.
     */
    @FXML
    public void logout(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Obtiene el usuario actual que ha iniciado sesión.
     *
     * @return Usuario actual que ha iniciado sesión.
     */
    public Usuario getCurrentUser() {
        return currentUser;
    }
}