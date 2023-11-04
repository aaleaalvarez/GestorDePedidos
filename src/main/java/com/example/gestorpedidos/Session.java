package com.example.gestorpedidos;


import lombok.Getter;

/**
 * Clase de utilidad para gestionar la sesión del usuario.
 * Almacena información sobre el usuario logeado y el pedido seleccionado.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public class Session {
    /**
     * Nombre del usuario logeado.
     * Obtiene el nombre del usuario logeado.
     */
    @Getter
    private static String usuarioLogeado;
    /**
     * Identificador del usuario logeado.
     * Obtiene el identificador del usuario logeado.
     */
    @Getter
    private static int usuarioId;
    /**
     * Código del pedido seleccionado.
     * Obtiene el código del pedido seleccionado.
     */
    @Getter
    private static String pedido;

    /**
     * Establece el nombre del usuario logeado.
     *
     * @param usuario El nombre del usuario logeado.
     */
    public static void setUsuarioLogeado(String usuario) {
        usuarioLogeado = usuario;
    }

    /**
     * Establece el identificador del usuario logeado.
     *
     * @param id El identificador del usuario logeado.
     */
    public static void setUsuarioId(int id) {
        usuarioId = id;
    }

    /**
     * Establece el código del pedido seleccionado.
     *
     * @param pedido El código del pedido seleccionado.
     */

    public static void setPedido(String pedido) {
        Session.pedido = pedido;
    }
}