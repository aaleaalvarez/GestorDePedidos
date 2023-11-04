package com.example.gestorpedidos.domain.Producto;

import java.util.List;

/**
 * Interfaz que define las operaciones para acceder y manipular productos en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public interface ProductoDAO {
    /**
     * Obtiene una lista de todos los productos disponibles en la base de datos.
     *
     * @return Lista de productos
     */
    List<Producto> findAll();
}
