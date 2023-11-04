package com.example.gestorpedidos.controllers;

import com.example.gestorpedidos.Session;
import com.example.gestorpedidos.domain.DBConnection;
import com.example.gestorpedidos.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidos.domain.ItemPedido.ItemPedidoDAO;
import com.example.gestorpedidos.domain.ItemPedido.ItemPedidoDAOImp;
import com.example.gestorpedidos.domain.Pedido.Pedido;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador encargado de la lógica de la vista que muestra los elementos asociados a un pedido.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public class ListItemPedidoController implements Initializable {
    /**
     * Instancia de la interfaz de acceso a datos para los elementos de pedido.
     */
    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOImp(DBConnection.getConnection());
    /**
     * Elementos de la interfaz de usuario definidos con anotaciones @FXML.
     */
    @javafx.fxml.FXML
    private MenuItem menuLogout;
    @javafx.fxml.FXML
    private MenuItem menuCerrar;
    @javafx.fxml.FXML
    private TableColumn idColumn;
    @javafx.fxml.FXML
    private TableColumn pedidoIdColumn;
    @javafx.fxml.FXML
    private TableColumn productoIdColumn;
    @javafx.fxml.FXML
    private TableColumn cantidadColumn;
    @javafx.fxml.FXML
    private TableView tablaItems;
    @javafx.fxml.FXML
    private Button volverButton;
    /**
     * Pedido actualmente seleccionado.
     */
    private Pedido pedido;

    @javafx.fxml.FXML
    public void initialize() {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpedidos/login-view.fxml\""));
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
     * Método de inicialización del controlador.
     *
     * @param url            URL de la ubicación del FXML a inicializar.
     * @param resourceBundle Recursos específicos del local.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatosItemPedido();
    }

    /**
     * Carga y muestra los elementos del pedido asociados al pedido actualmente seleccionado.
     */
    private void cargarDatosItemPedido() {
        System.out.println("Cargando items del pedido: " + Session.getPedido());

        if (itemPedidoDAO == null) {
            System.out.println("Error: itemPedidoDAO no ha sido inicializado");
            return;
        }

        List<ItemPedido> items = itemPedidoDAO.findItemsByPedidoCodigo(Session.getPedido());

        idColumn.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("id"));
        pedidoIdColumn.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("pedidoId"));
        productoIdColumn.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("productoId"));
        cantidadColumn.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("cantidad"));

        tablaItems.setItems(FXCollections.observableArrayList(items));
    }

    /**
     * Vuelve a la ventana de lista de pedidos desde la ventana de detalles de elementos de pedido.
     *
     * @param actionEvent Evento del botón de volver.
     */
    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpedidos/listPedidos-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) volverButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            ListPedidosController controller = loader.getController();
            controller.initialize(null, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}