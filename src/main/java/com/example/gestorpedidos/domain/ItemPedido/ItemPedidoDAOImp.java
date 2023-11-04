package com.example.gestorpedidos.domain.ItemPedido;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ItemPedidoDAO que proporciona métodos para acceder y manipular elementos de pedidos en una base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
@Log
public class ItemPedidoDAOImp implements ItemPedidoDAO {

    private final static String QUERY_FIND_ITEMS_BY_PEDIDO_CODIGO = "SELECT i.id, i.pedido_cod, i.cantidad, i.producto_id FROM Item_pedido i JOIN Pedido p ON i.pedido_cod = p.codigo WHERE i.pedido_cod = ?";
    private final Connection connection;

    /**
     * Constructor que recibe una conexión a la base de datos.
     *
     * @param c La conexión a la base de datos.
     */
    public ItemPedidoDAOImp(Connection c) {
        connection = c;
    }

    /**
     * Busca y devuelve una lista de elementos de pedido asociados a un pedido específico.
     *
     * @param pedidoId El identificador único del pedido.
     * @return Una lista de objetos ItemPedido asociados al pedido especificado.
     */
    @Override
    public List<ItemPedido> findItemsByPedidoCodigo(String pedidoId) {
        List<ItemPedido> items = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(QUERY_FIND_ITEMS_BY_PEDIDO_CODIGO)) {
            stmt.setString(1, pedidoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String pedido = rs.getString("pedido_cod");
                int cantidad = rs.getInt("cantidad");
                int productoId = rs.getInt("producto_id");

                ItemPedido item = new ItemPedido();
                item.setId(id);
                item.setPedidoId(pedido);
                item.setCantidad(cantidad);
                item.setProductoId(productoId);

                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }
}