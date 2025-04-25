package com.oxyl.epf.service;

import com.oxyl.epf.mapDTO;

import java.util.List;

public interface mapService {
    mapDTO create_map(mapDTO mapDTO);
    mapDTO read_map(int id);
    List<mapDTO> readAll_maps();
    mapDTO update_map(mapDTO mapDTO);
    boolean delete_map(int id);
}
