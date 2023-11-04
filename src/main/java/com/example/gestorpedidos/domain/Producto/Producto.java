package com.example.gestorpedidos.domain.Producto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa un producto en el sistema de gestión de pedidos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
@Data
@AllArgsConstructor
public class Producto implements Serializable {
    /**
     * Identificador único del producto.
     */
    private int id;
    /**
     * Nombre del producto.
     */
    private String nombre;
    /**
     * Precio del producto.
     */
    private double precio;
    /**
     * Cantidad disponible del producto en el inventario.
     */
    private int cantidadDisponible;
}
