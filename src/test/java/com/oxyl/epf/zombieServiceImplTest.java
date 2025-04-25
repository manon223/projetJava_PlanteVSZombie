package com.oxyl.epf;
import com.oxyl.epf.dao.zombieDAOImpl;
import com.oxyl.epf.service.zombieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class zombieServiceImplTest {
    private zombieServiceImpl zombieService;
    private zombieDAOImpl zombieDAO;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        DatabaseConfig dbConfig = new DatabaseConfig();
        jdbcTemplate=new JdbcTemplate(dbConfig.dataSource());

        zombieDAO=new zombieDAOImpl(jdbcTemplate);
        zombieService=new zombieServiceImpl(zombieDAO);

    }
    //@Test
    public void testCreateZombie() {
        zombieDTO dto=new zombieDTO("Football Zombie", 250, 0.90, 12, 0.60, "images/zombie/football.png", 3);
        zombieDTO created=zombieService.create_zombie(dto);
        assertNotNull(created);
        assertEquals("images/zombie/football.png",created.getChemin_image());
        //vérifications si map bien lisible:
        assertTrue(created.getId_zombie()>0);
        zombieDTO zombied=zombieService.read_zombie(created.getId_zombie());
        assertEquals(250,zombied.getPoint_de_vie());
    }
    //@Test
    public void testReadZombie() {
        zombieDTO dto=zombieService.read_zombie(35);
        assertNotNull(dto);
        assertEquals("images/zombie/football.png",dto.getChemin_image());
    }
    //@Test
    public void testReadAllZombies() {
        List<zombieDTO> zombieDTOS=zombieService.readAll_zombies();
        assertTrue(zombieDTOS.size()>=1);
    }
    //@Test
    public void TestfindZombieById_map() {
        List<zombieDTO> zombieDTOS=zombieService.findByID_map(1);
        assertTrue(zombieDTOS.size()>=1);
        assertNotNull(zombieDTOS, "La liste des zombies ne doit pas être vide.");
        assertEquals(3, zombieDTOS.size(), "Il doit y avoir 3 zombies dans la liste.");
        assertEquals("Zombie de base", zombieDTOS.get(0).getNom());
        assertEquals("Zombie Cone", zombieDTOS.get(1).getNom());
        assertEquals("Zombie Seau", zombieDTOS.get(2).getNom());

    }

   //@Test
    public void testUpdateZombie() {
        zombieDTO dto=zombieService.read_zombie(35);
        dto.setPoint_de_vie(300);
        dto.setChemin_image("images/zombie/f.png");
        zombieDTO updateZombie=zombieService.update_zombie(dto);
        assertEquals(300,updateZombie.getPoint_de_vie());
        assertEquals("images/zombie/f.png",updateZombie.getChemin_image());
    }
    //@Test
    public void testDeleteZombie() {
        zombieDTO dto=zombieService.read_zombie(35);
        boolean deleted =zombieService.delete_zombie(dto.getId_zombie());
        assertTrue(deleted);
        assertThrows(Exception.class, () -> zombieService.read_zombie(dto.getId_zombie()));
    }
}
