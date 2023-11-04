package com.example.gestorpedidos.domain.Pedido;

import java.util.List;

/**
 * Interfaz que define métodos para acceder y manipular la información de los pedidos en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public interface PedidoDAO {
    /**
     * Busca y devuelve una lista de pedidos asociados a un usuario específico.
     *
     * @param usuarioId ID del usuario cuyos pedidos se quieren buscar.
     * @return Lista de pedidos asociados al usuario.
     */
    List<Pedido> findByUsuarioId(int usuarioId);
}
