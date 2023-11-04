package com.example.gestorpedidos.domain.Usuario;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Usuario.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public interface UsuarioDAO {
    /**
     * Recupera una lista de todos los usuarios en el sistema.
     *
     * @return Lista de usuarios.
     */
    List<Usuario> findAll();
}
