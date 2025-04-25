package com.oxyl.epf;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConfigTest {

    //@Test
    void testDatabaseConnection() {
        // Instanciation manuelle du DataSource via la classe de configuration
        DatabaseConfig databaseConfig = new DatabaseConfig();
        DataSource dataSource = databaseConfig.dataSource();

        // Tester la connexion
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "Connexion établie !");
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }finally{
            System.out.println("Connexion terminée");
        }

    }
}
