package com.oxyl.epf.service;

import com.oxyl.epf.dao.zombieDAOImpl;
import com.oxyl.epf.exception.ResourceNotFoundException;
import com.oxyl.epf.zombie;
import com.oxyl.epf.zombieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class zombieServiceImpl implements zombieService {
    @Autowired
    private final com.oxyl.epf.dao.zombieDAOImpl zombieDAOImpl;
    public zombieServiceImpl(zombieDAOImpl zombieDAOImpl) {
        this.zombieDAOImpl = zombieDAOImpl;
    }

    @Override
    public zombieDTO create_zombie(zombieDTO zombieDTO) {
        zombie z=convertionEntite(zombieDTO);
        zombie createdZombie=zombieDAOImpl.create_zombie(z);
        return convertionDTO(createdZombie);
    }

    private zombieDTO convertionDTO(zombie z) {
        return new zombieDTO(
                z.getId_zombie(),
                z.getNom(),
                z.getPoint_de_vie(),
                z.getAttaque_par_seconde(),
                z.getDegat_attaque(),
                z.getVitesse_de_deplacement(),
                z.getChemin_image(),
                z.getId_map()
        );
    }


    private zombie convertionEntite(zombieDTO zombieDTO) {
        zombie z=new zombie();
        z.setId_zombie(zombieDTO.getId_zombie());
        z.setNom(zombieDTO.getNom());
        z.setPoint_de_vie(zombieDTO.getPoint_de_vie());
        z.setAttaque_par_seconde(zombieDTO.getAttaque_par_seconde());
        z.setDegat_attaque(zombieDTO.getDegat_attaque());
        z.setVitesse_de_deplacement(zombieDTO.getVitesse_de_deplacement());
        z.setChemin_image(zombieDTO.getChemin_image());
        z.setId_map(zombieDTO.getId_map());
        return z;
    }

    @Override
    public zombieDTO read_zombie(int id) {
        zombie z= zombieDAOImpl.read_zombie(id);
        if(z==null){
            throw new ResourceNotFoundException(id+"Zombie non trouvé");
        }
        return convertionDTO(z);
    }

    @Override
    public List<zombieDTO> readAll_zombies() {
        List<zombie> zombies=zombieDAOImpl.readAll_zombies();
        List<zombieDTO> zombieDTOS=new ArrayList<>();
        for(zombie z:zombies){
            zombieDTOS.add(convertionDTO(z));
        }
        return zombieDTOS;
    }

    @Override
    public List<zombieDTO> findByID_map(int id_map) {
        List<zombie> zombiesId=zombieDAOImpl.findByID_map(id_map);
        List<zombieDTO> zombieIdDTOS=new ArrayList<>();
        for(zombie z:zombiesId){
            zombieIdDTOS.add(convertionDTO(z));
        }
        return zombieIdDTOS;
    }

    @Override
    public zombieDTO update_zombie(zombieDTO zombieDTO) {
        zombie z=convertionEntite(zombieDTO);
        zombie updatedZombie=zombieDAOImpl.update_zombie(z);
        return convertionDTO(updatedZombie);
    }

    @Override
    public boolean delete_zombie(int id) {
        zombieDAOImpl.delete_zombie(id);
        return true;
    }
}
