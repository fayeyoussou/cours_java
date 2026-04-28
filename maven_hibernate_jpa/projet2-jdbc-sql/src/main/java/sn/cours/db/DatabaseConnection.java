package sn.cours.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connexion JDBC vers MySQL (port 3307)
public class DatabaseConnection {

    private static final String URL      = "jdbc:mysql://localhost:3307/gestion_produits?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = "root";

    private static Connection instance;

    public static Connection getConnection() throws SQLException {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return instance;
    }
}
