module com.example.gestorpedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    opens com.example.gestorpedidos to javafx.fxml;
    exports com.example.gestorpedidos;

    opens com.example.gestorpedidos.domain.Pedido to javafx.base;
    exports com.example.gestorpedidos.controllers;
    opens com.example.gestorpedidos.controllers to javafx.fxml;
    opens com.example.gestorpedidos.domain.ItemPedido to javafx.base;


}