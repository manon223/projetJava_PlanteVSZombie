package com.oxyl.epf;

import com.oxyl.epf.dao.mapDAOImpl;
import com.oxyl.epf.service.mapServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class mapServiceImplTest {
    private mapServiceImpl mapService;
    private mapDAOImpl mapDAO;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        DatabaseConfig dbConfig = new DatabaseConfig();
        jdbcTemplate=new JdbcTemplate(dbConfig.dataSource());

        mapDAO=new mapDAOImpl(jdbcTemplate);
        mapService=new mapServiceImpl(mapDAO);
    }

    //@Test
    public void testCreateMap() {
        mapDTO dto=new mapDTO(5,8,"map/map17.png");
        mapDTO created=mapService.create_map(dto);
        assertNotNull(created);
        assertEquals("map/map17.png",created.getChemin_image());
        //vérifications si map bien lisible:
        assertTrue(created.getId_map()>0);
        mapDTO mapd=mapService.read_map(created.getId_map());
        //assertNotNull(mapd);
        assertEquals(5,mapd.getLigne());
    }

    //@Test
    public void testReadMap() {
        mapDTO dto=mapService.read_map(9);
        assertNotNull(dto);
        assertEquals("map/map1.png",dto.getChemin_image());
    }
    //@Test
    public void testReadAllMap() {
        List<mapDTO> mapDTOS=mapService.readAll_maps();
        assertNotNull(mapDTOS);
        assertTrue(mapDTOS.size()>=1);
    }
    //@Test
    public void testUpdateMap() {
        mapDTO dto=mapService.read_map(1);
        dto.setLigne(7);
        dto.setChemin_image("images/map/gazon.png");
        mapDTO updateMap=mapService.update_map(dto);
        assertEquals(7,updateMap.getLigne());
        assertEquals("images/map/gazon.png",updateMap.getChemin_image());
    }
   //@Test
    public void testDeleteMap() {
        mapDTO dto=mapService.read_map(17);
        boolean deleted =mapService.delete_map(dto.getId_map());
        assertTrue(deleted);
        assertThrows(Exception.class, () -> mapService.read_map(dto.getId_map()));
    }
}
