package com.oxyl.epf;
import com.oxyl.epf.dao.mapDAOImpl;
import com.oxyl.epf.dao.zombieDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
// Tests associés à la map
public class mapDAOImplTest {
    private JdbcTemplate jdbcTemplate;
    private mapDAOImpl mapDAO;

    @BeforeEach
    public void setUp() {
        DatabaseConfig dbConfig = new DatabaseConfig();
        jdbcTemplate = new JdbcTemplate(dbConfig.dataSource());
        mapDAO = new mapDAOImpl(jdbcTemplate);
    }
   //@Test
    public void testCreateMap(){
        map m=new map(5,9,"/images/map4.png");
        mapDAO.create_map(m);
        map mCreate=mapDAO.read_map(8);
        assertNotNull(mCreate);
        assertEquals("/images/map4.png",mCreate.getChemin_image());
    }
    //@Test
    public void testReadMap(){
        map m=mapDAO.read_map(5);
        assertNotNull(m);
        assertEquals("/images/map4.png",m.getChemin_image());
    }
    //@Test
    public void testReadAllMap(){
        List<map> maps=mapDAO.readAll_maps();
        assertNotNull(maps);
        assertEquals(4,maps.size());
        assertEquals(5,maps.get(0).getLigne());
        assertEquals(6,maps.get(1).getLigne());
        assertEquals(4,maps.get(2).getLigne());
        assertEquals(5,maps.get(3).getLigne());
    }
    //@Test
    public void testUpdateMap(){
        map m=mapDAO.read_map(7);
        m.setChemin_image("/images/map/gazon.png");
        m.setLigne(4);
        m.setColonne(8);
        mapDAO.update_map(m);
        map mUpdate=mapDAO.read_map(7);
        assertNotNull(mUpdate);
        assertEquals("/images/map/gazon.png",mUpdate.getChemin_image());
    }
    //Test
    public void testDeleteMap(){ //24
        map m=mapDAO.read_map(10);
        zombieDAOImpl zombieDAO=new zombieDAOImpl(jdbcTemplate);
        List<zombie> zs= zombieDAO.findByID_map(10);
        mapDAO.delete_map(10);
        map mDelete=mapDAO.read_map(10);
        assertNull(mDelete);
    }


}
