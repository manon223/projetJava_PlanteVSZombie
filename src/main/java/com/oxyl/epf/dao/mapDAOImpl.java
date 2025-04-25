package com.oxyl.epf.dao;

import com.oxyl.epf.map;
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
public class mapDAOImpl implements mapDAO {
    private final JdbcTemplate jdbcTemplate;

    public mapDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<map> mapRowMapper(){
        return (rs, rowNum)-> new map(
                rs.getInt("id_map"),
                rs.getInt("ligne"),
                rs.getInt("colonne"),
                rs.getString("chemin_image")
        );
    }

    @Override
    public map create_map(map map) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql="INSERT INTO map(ligne,colonne,chemin_image) VALUES(?,?,?)";

        jdbcTemplate.update(connection ->{
                    PreparedStatement ps =connection.prepareStatement(
                            sql,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setInt(1,map.getLigne());
                    ps.setInt(2,map.getColonne());
                    ps.setString(3,map.getChemin_image());
                    return ps;
                }, keyHolder);
        Number key=keyHolder.getKey();
        if(key!=null){
            map.setId_map(key.intValue());
        }
        return map;
    }

    @Override
    public map read_map(int id) {
        String sql="SELECT * FROM map WHERE id_map=?";
        try{
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapRowMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<map> readAll_maps() {
        String sql="SELECT * FROM map";
        return jdbcTemplate.query(sql,mapRowMapper());
    }

    @Override
    public map update_map(map map) {
        String sql="UPDATE map SET ligne=?,colonne=?,chemin_image=? WHERE id_map=?;";
        jdbcTemplate.update(sql,map.getLigne(),map.getColonne(),map.getChemin_image(),map.getId_map());
        return map;
    }

    @Override
    public boolean delete_map(int id_map) {
        String sqlzombie = "UPDATE zombie SET id_map=NULL WHERE id_map=?;";
        jdbcTemplate.update(sqlzombie,id_map);

        String sql="DELETE FROM map WHERE id_map=?";
        jdbcTemplate.update(sql,id_map);

        return true;

    }
}
