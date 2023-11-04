package com.example.gestorpedidos.domain.Producto;

import com.example.gestorpedidos.domain.DBConnection;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ProductoDAO que proporciona métodos para acceder y manipular productos en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
@Log
public class ProductoDAOImp {

    private final static String QUERY_FIND_ALL = "SELECT * FROM Productos";
    private final static String QUERY_FIND_BY_USUARIO_ID = "SELECT código, fecha, total FROM Pedidos WHERE id_usuario = ?";
    private final Connection connection;

    /**
     * Constructor que inicializa una nueva instancia de ProductoDAOImp con una conexión a la base de datos.
     *
     * @param connection Conexión a la base de datos
     */
    public ProductoDAOImp(Connection connection) {
        this.connection = connection;
    }

    /**
     * Obtiene una lista de todos los productos disponibles en la base de datos.
     *
     * @return Lista de productos
     */
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(QUERY_FIND_ALL)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad_disponible")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            log.severe("Error al consultar productos: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return productos;
    }
}