package com.oxyl.epf;
import com.oxyl.epf.dao.planteDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class planteDAOImplTest {
    private JdbcTemplate jdbcTemplate;
    private planteDAOImpl planteDAO;

    @BeforeEach
    public void setUp() {
        DatabaseConfig dbConfig = new DatabaseConfig();
        jdbcTemplate = new JdbcTemplate(dbConfig.dataSource());
        planteDAO = new planteDAOImpl(jdbcTemplate);
    }
    //@Test
    public void testCreatePlante() {
        plante p=new plante("tournesol",99,0.00,0,50,25.00,"normal","/images/tournesol.png");
        planteDAO.create_plante(p);
        plante retrievedPlante=planteDAO.read_plante(6);
        assertNotNull(retrievedPlante);
        assertEquals("tournesol",retrievedPlante.getNom());
    }
    //@Test
    public void testReadPlante() {
        plante p=planteDAO.read_plante(6);
        assertNotNull(p);
        assertEquals("tournesol",p.getNom());
    }
   //@Test
    public void testReadAllPlantes() {
        List<plante> plantes=planteDAO.readAll_plantes();
        assertNotNull(plantes);
        assertEquals(6,plantes.size());
        assertEquals("Tournesol",plantes.get(0).getNom());
        assertEquals("Pois Tireur",plantes.get(1).getNom());
        assertEquals("Double Pisto P",plantes.get(2).getNom());
        assertEquals("Glace Pois",plantes.get(3).getNom());
        assertEquals("Noix",plantes.get(4).getNom());
        assertEquals("tournesol",plantes.get(5).getNom());
    }
    //@Test
    public void testUpdatePlante() {
        plante p=planteDAO.read_plante(9);
        p.setNom("Tournesol");
        p.setPoint_de_vie(100);
        p.setAttaque_par_seconde(0.00);
        p.setDegat_attaque(0);
        p.setCout(50);
        p.setSoleil_par_seconde(25.00);
        p.setEffet("normal");
        p.setChemin_image("images/plante/tournesol.png");
        planteDAO.update_plante(p);
        plante pUpdated=planteDAO.read_plante(9);
        assertNotNull(pUpdated);
        assertEquals("Tournesol",pUpdated.getNom());
    }
    //@Test
    public void testDeletePlante() {
        plante p=planteDAO.read_plante(10);
        boolean reponse=planteDAO.delete_plante(10);
        System.out.println(reponse);
        plante pDeleted=planteDAO.read_plante(10);
        assertNull(pDeleted);

    }


}
