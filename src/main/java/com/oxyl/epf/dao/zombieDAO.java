package com.oxyl.epf.dao;

import com.oxyl.epf.zombie;

import java.util.List;

public interface zombieDAO {
    zombie create_zombie(zombie zombie);
    zombie read_zombie(int id);
    List<zombie> readAll_zombies();
    List<zombie> findByID_map(int id_map);
    zombie update_zombie(zombie zombie);
    boolean delete_zombie(int id);
}
