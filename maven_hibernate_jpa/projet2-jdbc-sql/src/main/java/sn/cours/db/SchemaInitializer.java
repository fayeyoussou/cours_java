package sn.cours.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// Crée les tables SQL au démarrage de l'application
public class SchemaInitializer {

    public static void init() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS categorie (
                    id      BIGINT PRIMARY KEY AUTO_INCREMENT,
                    code    VARCHAR(20)  NOT NULL UNIQUE,
                    libelle VARCHAR(200) NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS produit (
                    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
                    code         VARCHAR(20)  NOT NULL UNIQUE,
                    libelle      VARCHAR(200) NOT NULL,
                    id_categorie BIGINT NOT NULL,
                    FOREIGN KEY (id_categorie) REFERENCES categorie(id)
                )
            """);
        }
    }
}
