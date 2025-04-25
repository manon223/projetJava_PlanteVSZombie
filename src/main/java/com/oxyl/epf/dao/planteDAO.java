package com.oxyl.epf.dao;

import com.oxyl.epf.plante;

import java.util.List;

public interface planteDAO {
    plante create_plante(plante plante);
    plante read_plante(int id);
    List<plante> readAll_plantes();
    plante update_plante(plante plante);
    boolean delete_plante(int id);
}
