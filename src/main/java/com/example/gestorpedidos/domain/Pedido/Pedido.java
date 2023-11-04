package com.example.gestorpedidos.domain.Pedido;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa un pedido en el sistema.
 * Contiene información como el identificador, código, fecha, ID de usuario asociado y el total del pedido.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
@Data
public class Pedido implements Serializable {
    /**
     * Identificador único del pedido.
     */
    private int id;
    /**
     * Código del pedido.
     */
    private String codigo;
    /**
     * Fecha en la que se realizó el pedido.
     */
    private Date fecha;
    /**
     * ID del usuario asociado al pedido.
     */
    private int usuario_id;
    /**
     * Total del pedido.
     */
    private double total;
}
