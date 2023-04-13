package com.example.movies_manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DataConnection class represents a data connection for accessing a database.
 * It provides methods for getting a database connection.
 */
public class DataConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    /**
     * Retrieves a database connection.
     *
     * @return The database connection.
     * @throws RuntimeException If there is an error establishing the database connection.
     */
    public Connection getConnection() {
        Connection sqlCon;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            sqlCon = DriverManager
                    .getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sqlCon;

    }
}
