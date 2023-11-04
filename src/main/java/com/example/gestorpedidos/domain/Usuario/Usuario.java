package com.example.gestorpedidos.domain.Usuario;

import lombok.Data;

import java.io.Serializable;

/**
 * Clase que representa a un usuario del sistema.
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
@Data
public class Usuario implements Serializable {
    /**
     * ID único del usuario.
     */
    private int id;
    /**
     * Nombre del usuario.
     */
    private String nombre;
    /**
     * Contraseña del usuario.
     */
    private String contraseña;
    /**
     * Dirección de correo electrónico del usuario.
     */
    private String email;
}
