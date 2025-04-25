package com.oxyl.epf;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@Configuration // cette classe est une classe de configuration

public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        //dataSource.setUrl("jdbc:mysql://localhost:3306/pvz");
        // Configuration du DataSource avec les informations de connexion à MySQL
        dataSource.setUser("epf");
        dataSource.setPassword("mot_de_passe");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("pvz");

        return dataSource; // Retourne l'objet DataSource configuré
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource); // Création d'un JdbcTemplate avec le DataSource
    }


    // Méthode pour tester la connexion et la fermer immédiatement
    @Bean
    public boolean testAndCloseConnection(DataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            System.out.println("Connexion réussie !");
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connexion fermée.");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture : " + e.getMessage());
                }
            }
        }
    }
}



