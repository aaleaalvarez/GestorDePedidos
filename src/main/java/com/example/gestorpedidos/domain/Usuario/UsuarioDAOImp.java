package com.example.gestorpedidos.domain.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de UsuarioDAO que proporciona métodos para acceder y manipular datos de usuarios en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */

public class UsuarioDAOImp implements UsuarioDAO {

    private final static String queryLoadAll = "SELECT * FROM Usuario";
    private Connection connection;

    /**
     * Constructor que recibe una conexión a la base de datos.
     *
     * @param connection Conexión a la base de datos.
     */
    public UsuarioDAOImp(Connection connection) {
        this.connection = connection;
    }

    /**
     * Constructor por defecto.
     */
    public UsuarioDAOImp() {

    }

    /**
     * Recupera una lista de todos los usuarios en la base de datos.
     *
     * @return Lista de usuarios.
     * @throws RuntimeException Si hay un error al acceder a la base de datos.
     */
    @Override
    public List<Usuario> findAll() {
        var usuarios = new ArrayList<Usuario>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(queryLoadAll);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId((int) rs.getLong("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuarios;
    }
}
