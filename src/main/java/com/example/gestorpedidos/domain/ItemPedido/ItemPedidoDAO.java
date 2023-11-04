package com.example.gestorpedidos.domain.ItemPedido;

import java.util.List;

/**
 * Interfaz que define las operaciones para acceder a los elementos de un pedido en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public interface ItemPedidoDAO {
    /**
     * Obtiene una lista de elementos de pedido asociados a un pedido específico.
     *
     * @param pedidoId El identificador único del pedido.
     * @return Una lista de objetos ItemPedido asociados al pedido especificado.
     */
    List<ItemPedido> findItemsByPedidoCodigo(String pedidoId);
}
