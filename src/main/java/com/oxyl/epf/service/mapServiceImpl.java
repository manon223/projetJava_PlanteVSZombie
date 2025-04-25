package com.oxyl.epf.service;

import com.oxyl.epf.dao.mapDAOImpl;
import com.oxyl.epf.exception.ResourceNotFoundException;
import com.oxyl.epf.map;
import com.oxyl.epf.mapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class mapServiceImpl implements mapService {
    @Autowired
    private final com.oxyl.epf.dao.mapDAOImpl mapDAOImpl;

    public mapServiceImpl(mapDAOImpl mapDAOImpl) {
        this.mapDAOImpl = mapDAOImpl;
    }

    @Override
    public mapDTO create_map(mapDTO mapDTO) {
        map p=convertionEntite(mapDTO);
        map createdMap=mapDAOImpl.create_map(p);
        return convertionDTO(createdMap);
    }

    private mapDTO convertionDTO(map m) {
        return new mapDTO(
                m.getId_map(),
                m.getLigne(),
                m.getColonne(),
                m.getChemin_image()
        );
    }

    private map convertionEntite(mapDTO mapDTO) {
        map map=new map();
        map.setId_map(mapDTO.getId_map());
        map.setLigne(mapDTO.getLigne());
        map.setColonne(mapDTO.getColonne());
        map.setChemin_image(mapDTO.getChemin_image());
        return map;
    }

    @Override
    public mapDTO read_map(int id) {
        map m=mapDAOImpl.read_map(id);
        if(m==null){
            throw new ResourceNotFoundException(id+" map non trouvée");
        }
        return convertionDTO(m);
    }

    @Override
    public List<mapDTO> readAll_maps() {
        List<map> maps=mapDAOImpl.readAll_maps();
        List<mapDTO> mapDTOs=new ArrayList<mapDTO>();
        for(map m:maps){
            mapDTOs.add(convertionDTO(m));
        }
        return mapDTOs;
    }

    @Override
    public mapDTO update_map(mapDTO mapDTO) {
        map m=convertionEntite(mapDTO);
        map updatedMap=mapDAOImpl.update_map(m);
        return convertionDTO(updatedMap);
    }

    @Override
    public boolean delete_map(int id) {
        mapDAOImpl.delete_map(id);
        return true;
    }
}
