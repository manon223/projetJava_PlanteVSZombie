package com.oxyl.epf;
import com.oxyl.epf.dao.zombieDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class zombieDAOImplTest {
    private JdbcTemplate jdbcTemplate;
    private zombieDAOImpl zombieDAO;

    @BeforeEach
    public void setUp() {
        DatabaseConfig dbConfig = new DatabaseConfig();
        jdbcTemplate =new JdbcTemplate(dbConfig.dataSource());
        zombieDAO = new zombieDAOImpl(jdbcTemplate);

    }
    //@Test
    public void testCreateZombie() {
        zombie z = new zombie("Football Zombie", 250, 0.90, 12, 0.60, "images/zombie/football.png", 3);
        zombieDAO.create_zombie(z);
        zombie retrievedZombie=zombieDAO.read_zombie(33);
        assertNotNull(retrievedZombie);
        assertEquals("Football Zombie", retrievedZombie.getNom());
    }
    //@Test
    public void testfindByID_map() {
        List<zombie> zombies = zombieDAO.findByID_map(1);

        assertNotNull(zombies, "La liste des zombies ne doit pas être vide.");
        assertEquals(3, zombies.size(), "Il doit y avoir 3 zombies dans la liste.");
        assertEquals("Zombie de base", zombies.get(0).getNom());
        assertEquals("Zombie Cone", zombies.get(1).getNom());
        assertEquals("Zombie Seau", zombies.get(2).getNom());
        //assertEquals("classique", zombies.get(3).getNom());
    }
   //@Test
    public void testReadZombie() {
        //zombie z = new zombie("classique", 100, 1.0, 10, 1.5, "/images/classique.png", 1);
        //zombieDAO.create_zombie(z);
        zombie retrievedZombie = zombieDAO.read_zombie(25);
        assertNotNull(retrievedZombie);
        assertEquals("classique", retrievedZombie.getNom());
    }
    //@Test
    public void testReadAllZombies() {
        List<zombie> zombies = zombieDAO.readAll_zombies();

        assertNotNull(zombies, "La liste des zombies ne doit pas être vide.");
        assertEquals(4, zombies.size(), "Il doit y avoir 2 zombies dans la liste.");
        assertEquals("Zombie de base", zombies.get(0).getNom());
        assertEquals("Zombie Cone", zombies.get(1).getNom());
        assertEquals("Zombie Seau", zombies.get(2).getNom());
        assertEquals("classique", zombies.get(3).getNom());

    }
    //@Test
    public void testUpdateZombie() {
        zombie z = zombieDAO.read_zombie(33);
        System.out.println(z.getNom());
        //z.setNom("Zombie de base");
        //z.setPoint_de_vie(100);
        //z.setAttaque_par_seconde(0.80);
        //z.setDegat_attaque(10);
        //z.setVitesse_de_deplacement(0.50);
        //z.setChemin_image("images/zombie/zombie.png");
        z.setId_map(7);
        zombieDAO.update_zombie(z);
        zombie updatedZombie = zombieDAO.read_zombie(33);
        assertNotNull(updatedZombie);
        //assertEquals("Zombie de base", updatedZombie.getNom());
        assertEquals(7, updatedZombie.getId_map());
    }
    //@Test
    void testDeleteZombie() { //45
        // Lire et afficher le zombie avant suppression
        zombie deletedZombie = zombieDAO.read_zombie(36);
        System.out.println(deletedZombie.getNom());

        zombieDAO.delete_zombie(36);

        zombie zombieAfterDeletion = zombieDAO.read_zombie(36);
        assertNull(zombieAfterDeletion);
    }



}
