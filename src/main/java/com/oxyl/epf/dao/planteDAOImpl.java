package com.oxyl.epf.dao;

import com.oxyl.epf.plante;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class planteDAOImpl implements planteDAO {
    private final JdbcTemplate jdbcTemplate;

    public planteDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<plante> planteRowMapper(){
        return (rs, rowNum)-> new plante(
                rs.getInt("id_plante"),
                rs.getString("nom"),
                rs.getInt("point_de_vie"),
                rs.getDouble("attaque_par_seconde"),
                rs.getInt("degat_attaque"),
                rs.getInt("cout"),
                rs.getDouble("soleil_par_seconde"),
                rs.getString("effet"),
                rs.getString("chemin_image")
        );
    }

    @Override
    public plante create_plante(plante plante) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql="INSERT INTO plante (nom,point_de_vie,attaque_par_seconde,degat_attaque,cout,soleil_par_seconde,effet,chemin_image) VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(connection->{
                    PreparedStatement ps=connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, plante.getNom());
                    ps.setInt(2, plante.getPoint_de_vie());
                    ps.setDouble(3, plante.getAttaque_par_seconde());
                    ps.setDouble(4, plante.getDegat_attaque());
                    ps.setInt(5, plante.getCout());
                    ps.setDouble(6, plante.getSoleil_par_seconde());
                    ps.setString(7, plante.getEffet());
                    ps.setString(8, plante.getChemin_image());
                    return ps;
                }, keyHolder);
        Number key = (Number) keyHolder.getKey();
        if(key!=null){
            plante.setId_plante(key.intValue());
        }
        return plante;
    }

    @Override
    public plante read_plante(int id) {
        String sql="SELECT * FROM plante WHERE id_plante=?";
    try{
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, planteRowMapper());
    }catch(
    EmptyResultDataAccessException e){
        return null;
    }
    }

    @Override
    public List<plante> readAll_plantes() {
        String sql="SELECT * FROM plante";
        return jdbcTemplate.query(sql,planteRowMapper());
    }

    @Override
    public plante update_plante(plante plante) {
        String sql="UPDATE plante SET nom=?,point_de_vie=?,attaque_par_seconde=?,degat_attaque=?,cout=?,soleil_par_seconde=?,effet=?,chemin_image=? WHERE id_plante=?;";
        jdbcTemplate.update(sql,plante.getNom(),plante.getPoint_de_vie(),plante.getAttaque_par_seconde(),plante.getDegat_attaque(),plante.getCout(),plante.getSoleil_par_seconde(),plante.getEffet(),plante.getChemin_image(),plante.getId_plante());
        return plante;
    }

    @Override
    public boolean delete_plante(int id_plante) {
        String sql="DELETE FROM plante WHERE id_plante=?";
        jdbcTemplate.update(sql,id_plante);
        return true;
    }

}
