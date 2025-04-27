package com.oxyl.epf.dao;

import com.oxyl.epf.zombie;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class zombieDAOImpl implements zombieDAO {

    private final JdbcTemplate jdbcTemplate;

    public zombieDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public zombie create_zombie(zombie zombie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql="INSERT INTO zombie (nom,point_de_vie,attaque_par_seconde, degat_attaque, vitesse_de_deplacement, chemin_image, id_map) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection-> {
                    PreparedStatement ps = connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, zombie.getNom());
                    ps.setDouble(2,zombie.getPoint_de_vie());
                    ps.setDouble(3,zombie.getAttaque_par_seconde());
                    ps.setDouble(4,zombie.getDegat_attaque());
                    ps.setDouble(5,zombie.getVitesse_de_deplacement());
                    ps.setString(6,zombie.getChemin_image());
                    ps.setInt(7,zombie.getId_map());
                    return ps;
                },keyHolder);
        Number key=keyHolder.getKey();
        if(key!=null){
            zombie.setId_zombie(key.intValue());
        }
        return zombie;
    }

    @Override
    public zombie read_zombie(int id) {
        String sql="SELECT * FROM zombie WHERE id_zombie=?";
        try{
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, zombieRowMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private RowMapper<zombie> zombieRowMapper() {
        //récupérer infos de la bdd
        return (rs, rowNum) -> new zombie(
                rs.getInt("id_zombie"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getDouble("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getDouble("vitesse_de_deplacement"),
                rs.getString("chemin_image"),
                rs.getInt("id_map")
        );

    }
    //récupérer tous les zombies
    @Override
    public List<zombie> readAll_zombies() {
        String sql="SELECT * FROM zombie";
        return jdbcTemplate.query(sql, zombieRowMapper());
    }
    //récupérer les zombies en fonction de sa map associé
    @Override
    public List<zombie> findByID_map(int id_map) {
        String sql = "SELECT * FROM zombie WHERE id_map = ?";
        return jdbcTemplate.query(sql,new Object[]{id_map},zombieRowMapper());
    }

    //mettre à jour
    @Override
    public zombie update_zombie(zombie zombie) {
        String sql = "UPDATE zombie SET nom=?, point_de_vie=?, attaque_par_seconde=?, degat_attaque=?, vitesse_de_deplacement=?, chemin_image=?, id_map=? WHERE id_zombie=?;";
        jdbcTemplate.update(sql,zombie.getNom(),zombie.getPoint_de_vie(),zombie.getAttaque_par_seconde(),zombie.getDegat_attaque(),zombie.getVitesse_de_deplacement(),zombie.getChemin_image(),zombie.getId_map(),zombie.getId_zombie() );
        return zombie;
    }


    //supprimer
    @Override
    public boolean delete_zombie(int id_zombie) {
        String sql="DELETE FROM zombie WHERE id_zombie=?";
        jdbcTemplate.update(sql, id_zombie);
        return true;
    }

}
