package com.example.gestorpedidos.domain.Pedido;

import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz PedidoDAO que proporciona métodos para acceder y manipular la información de los pedidos en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
@Log
public class PedidoDAOImp implements PedidoDAO {

    private final static String QUERY_FIND_BY_USUARIO_ID = "SELECT id, codigo, fecha, usuario_id, total FROM Pedido WHERE usuario_id = ?";
    private final Connection connection;

    /**
     * Constructor de la clase PedidoDAOImp.
     *
     * @param c Objeto de conexión a la base de datos.
     */
    public PedidoDAOImp(Connection c) {
        connection = c;
    }

    /**
     * Busca y devuelve una lista de pedidos asociados a un usuario específico.
     *
     * @param usuarioId ID del usuario cuyos pedidos se quieren buscar.
     * @return Lista de pedidos asociados al usuario.
     */
    @Override
    public List<Pedido> findByUsuarioId(int usuarioId) {
        List<Pedido> pedidos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(QUERY_FIND_BY_USUARIO_ID)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                Date fecha = rs.getDate("fecha");
                int usuario_id = rs.getInt("usuario_id");
                Double total = rs.getDouble("total");

                Pedido pedido = new Pedido();
                pedido.setId(id);
                pedido.setCodigo(codigo);
                pedido.setFecha(fecha);
                pedido.setUsuario_id(usuario_id);
                pedido.setTotal(total);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidos;
    }
}
