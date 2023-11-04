package com.example.gestorpedidos.domain;

import com.example.gestorpedidos.Application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Clase para establecer una conexión a la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 04/11/2023
 */
public class DBConnection {
    private static final Connection connection;// Instancia única de la conexión a la base de datos
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    /**
     * Bloque estático que se ejecuta cuando la clase es cargada por primera vez.
     */
    static {
        String url;
        String user;
        String password;

        var cfg = new Properties();
        try {
            cfg.load(Application.class.getClassLoader().getResourceAsStream("bbdd.properties"));
            logger.info("Configuración cargada");
            url = "jdbc:mysql://" + cfg.getProperty("host") + ":" + cfg.getProperty("port") + "/" + cfg.getProperty("dbname");
            logger.info(url);
            user = cfg.getProperty("user");
            password = cfg.getProperty("pass");
        } catch (IOException e) {
            logger.severe("Error procesando configuración.");
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene la instancia única de la conexión a la base de datos.
     *
     * @return Objeto Connection que representa la conexión a la base de datos.
     */
    public static Connection getConnection() {
        return connection;
    }
}
