package com.oxyl.epf.service;

import com.oxyl.epf.planteDTO;

import java.util.List;

public interface planteService {

    planteDTO create_plante(planteDTO planteDTO);
    planteDTO read_plante(int id);
    List<planteDTO> readAll_plantes();
    planteDTO update_plante(planteDTO planteDTO);
    boolean delete_plante(int id);
}
