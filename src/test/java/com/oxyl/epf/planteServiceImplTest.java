package com.oxyl.epf;
import com.oxyl.epf.dao.planteDAOImpl;
import com.oxyl.epf.service.planteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class planteServiceImplTest {
    private JdbcTemplate jdbcTemplate;
    private planteServiceImpl planteService;
    private planteDAOImpl planteDAO;

    @BeforeEach
    public void setUp() {
        DatabaseConfig dbConfig = new DatabaseConfig();
        jdbcTemplate =new JdbcTemplate(dbConfig.dataSource());

        planteDAO=new planteDAOImpl(jdbcTemplate);
        planteService=new planteServiceImpl(planteDAO);
    }
    //@Test
    public void testCreatePlante() {
        planteDTO dto= new planteDTO("tournesol",99,0.00,0,50,25.00,"normal","/images/tournesol.png");
        planteDTO created=planteService.create_plante(dto);
        assertNotNull(created);
        assertEquals("/images/tournesol.png",created.getChemin_image());
        assertTrue(created.getId_plante()>0);
        planteDTO planted=planteService.read_plante(created.getId_plante());
        assertEquals("tournesol",planted.getNom());

    }
    //@Test
    public void testReadPlante() {
        planteDTO dto=planteService.read_plante(7);
        assertNotNull(dto);
        assertEquals("/images/tournesol.png",dto.getChemin_image());
    }
    //@Test
    public void testReadAllPlante() {
        List<planteDTO> planteDTOS=planteService.readAll_plantes();
        assertNotNull(planteDTOS);
        assertTrue(planteDTOS.size()>1);
    }
    //@Test
    public void testUpdatePlante() {
        planteDTO dto=planteService.read_plante(7);
        dto.setNom("tulipe");
        dto.setChemin_image("/images/tulipe.png");
        planteDTO updatedPlante=planteService.update_plante(dto);
        assertEquals("tulipe",updatedPlante.getNom());
        assertEquals("/images/tulipe.png",updatedPlante.getChemin_image());
    }
    //@Test
    public void testDeletePlante() {
        planteDTO dto=planteService.read_plante(7);
        boolean deleted=planteService.delete_plante(dto.getId_plante());
        assertTrue(deleted);


    }
}
