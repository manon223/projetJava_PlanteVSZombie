package com.oxyl.epf.dao;

import com.oxyl.epf.map;

import java.util.List;

public interface mapDAO {
    map create_map(map map);
    map read_map(int id);
    List<map> readAll_maps();
    map update_map(map map);
    boolean delete_map(int id);
}
