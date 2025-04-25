package com.oxyl.epf.service;

import com.oxyl.epf.dao.planteDAOImpl;
import com.oxyl.epf.exception.ResourceNotFoundException;
import com.oxyl.epf.plante;
import com.oxyl.epf.planteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class planteServiceImpl implements planteService {
    @Autowired
    private final com.oxyl.epf.dao.planteDAOImpl planteDAOImpl;

    public planteServiceImpl(planteDAOImpl planteDAOImpl) {
        this.planteDAOImpl = planteDAOImpl;
    }

   @Override
    public planteDTO create_plante(planteDTO planteDTO){
        plante p=conversionEntite(planteDTO);
        plante createdPlante=planteDAOImpl.create_plante(p);
        return convertionDTO(createdPlante);
    }

    private planteDTO convertionDTO(plante p) {
        return new planteDTO(
                p.getId_plante(),
                p.getNom(),
                p.getPoint_de_vie(),
                p.getAttaque_par_seconde(),
                p.getDegat_attaque(),
                p.getCout(),
                p.getSoleil_par_seconde(),
                p.getEffet(),
                p.getChemin_image()
        );
    }

    private plante conversionEntite(planteDTO planteDTO) {
        plante p=new plante();
        p.setId_plante(planteDTO.getId_plante());
        p.setNom(planteDTO.getNom());
        p.setPoint_de_vie(planteDTO.getPoint_de_vie());
        p.setAttaque_par_seconde(planteDTO.getAttaque_par_seconde());
        p.setDegat_attaque(planteDTO.getDegat_attaque());
        p.setCout(planteDTO.getCout());
        p.setSoleil_par_seconde(planteDTO.getSoleil_par_seconde());
        p.setEffet(planteDTO.getEffet());
        p.setChemin_image(planteDTO.getChemin_image());
        return p;

    }

    public planteDTO read_plante(int id){
        plante p=planteDAOImpl.read_plante(id);
        if(p==null){
            throw new ResourceNotFoundException(id+" Plante non trouvée");
        }
        return convertionDTO(p); //dans mon controller je vais fait attention si id existe pas ...
    }

    @Override
    public List<planteDTO> readAll_plantes() {
        List<plante> plantes=planteDAOImpl.readAll_plantes();
        List<planteDTO> planteDTOs=new ArrayList<planteDTO>();
        for(plante p:plantes){
            planteDTOs.add(convertionDTO(p));
        }
        return planteDTOs;
    }

    @Override
    public planteDTO update_plante(planteDTO planteDTO) {
        plante p=conversionEntite(planteDTO);
        plante updatedPlante=planteDAOImpl.update_plante(p);
        return convertionDTO(updatedPlante);
    }

    @Override
    public boolean delete_plante(int id) {
        planteDAOImpl.delete_plante(id);
        return true;
    }


}
