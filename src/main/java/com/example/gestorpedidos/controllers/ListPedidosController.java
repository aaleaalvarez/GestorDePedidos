package com.example.gestorpedidos.controllers;

import com.example.gestorpedidos.Session;
import com.example.gestorpedidos.domain.DBConnection;
import com.example.gestorpedidos.domain.Pedido.Pedido;
import com.example.gestorpedidos.domain.Pedido.PedidoDAO;
import com.example.gestorpedidos.domain.Pedido.PedidoDAOImp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador encargado de la lógica de la vista que muestra la lista de pedidos asociados a un usuario.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public class ListPedidosController implements Initializable {
    /**
     * Elementos de la interfaz de usuario definidos con anotaciones @FXML.
     */
    @javafx.fxml.FXML
    private Label usuarioActivo;
    @javafx.fxml.FXML
    private TableView tabla;
    @javafx.fxml.FXML
    private TableColumn idColumn;
    @javafx.fxml.FXML
    private TableColumn CodColumn;
    @javafx.fxml.FXML
    private TableColumn FechaColumn;
    @javafx.fxml.FXML
    private TableColumn TotalColumn;
    @javafx.fxml.FXML
    private MenuItem menuLogout;
    @javafx.fxml.FXML
    private MenuItem menuCerrar;
    @javafx.fxml.FXML
    private TableColumn UsuarioIdColumn;

    /**
     * Método de inicialización del controlador.
     *
     * @param url            URL de la ubicación del FXML a inicializar.
     * @param resourceBundle Recursos específicos del local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String nombreUsuario = Session.getUsuarioLogeado(); // Reemplaza esto con el código real para obtener el nombre de usuario
        usuarioActivo.setText("Usuario activo: " + nombreUsuario);

        PedidoDAO pedidoDAO = new PedidoDAOImp(DBConnection.getConnection());
        List<Pedido> pedidos = pedidoDAO.findByUsuarioId(Session.getUsuarioId());

        idColumn.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("id"));
        CodColumn.setCellValueFactory(new PropertyValueFactory<Pedido, String>("codigo"));
        FechaColumn.setCellValueFactory(new PropertyValueFactory<Pedido, Date>("fecha"));
        UsuarioIdColumn.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("usuario_id"));
        TotalColumn.setCellValueFactory(new PropertyValueFactory<Pedido, Double>("total"));
        tabla.setItems(FXCollections.observableArrayList(pedidos));
    }

    /**
     * Cierra la ventana actual y abre la ventana de inicio de sesión.
     *
     * @param actionEvent Evento del botón de cierre de sesión.
     */
    @javafx.fxml.FXML
    public void Logout(ActionEvent actionEvent) {
        Stage stage = (Stage) ((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow();
        stage.close();

        // Abre la ventana de login
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpedidos/login-view.fxml"));
            Parent root = loader.load();
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cierra la aplicación.
     *
     * @param actionEvent Evento del botón de cierre de la aplicación.
     */
    @javafx.fxml.FXML
    public void Cerrar(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Muestra la ventana de detalles de un pedido seleccionado.
     *
     * @param pedido Pedido seleccionado para ver los detalles.
     * @throws IOException Excepción lanzada si hay un error al cargar la vista de detalles del pedido.
     */
    private void mostrarVentanaItemPedido(Pedido pedido) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpedidos/listItemPedido-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Detalles del pedido");
        stage.show();
    }

    /**
     * Maneja el evento de hacer clic en un pedido para ver sus detalles.
     *
     * @param event Evento de hacer clic en un pedido en la tabla.
     * @throws IOException Excepción lanzada si hay un error al cargar la vista de detalles del pedido.
     */
    @javafx.fxml.FXML
    public void onViewItemsClick(Event event) throws IOException {
        Pedido pedidoSeleccionado = (Pedido) tabla.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            // Cierra la ventana actual
            Stage stageActual = (Stage) usuarioActivo.getScene().getWindow();
            stageActual.close();

            String CodPedido = pedidoSeleccionado.getCodigo();
            Session.setPedido(CodPedido);
            mostrarVentanaItemPedido(pedidoSeleccionado);
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Pedido");
            alert.setHeaderText(null);
            alert.setContentText("No has seleccionado ningún pedido.");
            alert.showAndWait();
        }
    }
}

