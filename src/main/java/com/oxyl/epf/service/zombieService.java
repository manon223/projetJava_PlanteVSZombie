package com.oxyl.epf.service;

import com.oxyl.epf.zombieDTO;

import java.util.List;

public interface zombieService {
    zombieDTO create_zombie(zombieDTO zombieDTO);
    zombieDTO read_zombie(int id);
    List<zombieDTO> readAll_zombies();
    List<zombieDTO> findByID_map(int id_map);
    zombieDTO update_zombie(zombieDTO zombieDTO);
    boolean delete_zombie(int id);
}
