package com.example.gestorpedidos;

import com.example.gestorpedidos.controllers.LoginController;
import com.example.gestorpedidos.domain.DBConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

/**
 * Clase principal de la aplicación que extiende javafx.application.Application.
 * Es responsable de iniciar la aplicación y cargar la ventana de inicio de sesión.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    /**
     * Método start() que se ejecuta cuando se inicia la aplicación.
     *
     * @param stage Objeto Stage que representa la ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar la ventana de inicio de sesión.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Obtener una conexión a la base de datos
        Connection connection = DBConnection.getConnection();

        // Crear una instancia de LoginController con la conexión
        LoginController loginController = new LoginController();

        // Establecer el controlador en el FXMLLoader
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/com/example/gestorpedidos/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gestor de pedidos");
        stage.setScene(scene);
        stage.show();
    }
}