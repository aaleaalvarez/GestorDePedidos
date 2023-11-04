package com.example.gestorpedidos.domain.ItemPedido;

import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un elemento de un pedido.
 * Implementa la interfaz Serializable para permitir la serialización de objetos de esta clase.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
@Data
public class ItemPedido implements Serializable {
    /**
     * Identificador único del elemento del pedido.
     */
    private int id;
    /**
     * Identificador del pedido al que pertenece el elemento.
     */
    private String pedidoId;
    /**
     * Cantidad del producto asociado al elemento del pedido.
     */
    private int cantidad;
    /**
     * Identificador del producto asociado al elemento del pedido.
     */
    private int productoId;
}
