package com.oxyl.epf.controller;

import com.oxyl.epf.zombieDTO;
import com.oxyl.epf.service.zombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zombies")
public class zombieController {
    private final com.oxyl.epf.service.zombieService zombieService;

    @Autowired
    public zombieController(zombieService zombieService) {
        this.zombieService = zombieService;
    }
    @PostMapping
    public ResponseEntity<zombieDTO> createZombie(@RequestBody zombieDTO zombieDTO) {
        zombieDTO createdZombie=zombieService.create_zombie(zombieDTO);
        return new ResponseEntity<>(createdZombie,HttpStatus.CREATED);

    }
    @GetMapping("/{id_zombie}")
    public ResponseEntity<zombieDTO> readZombie(@PathVariable("id_zombie") int id) {
        zombieDTO zombie=zombieService.read_zombie(id);
        return ResponseEntity.ok(zombie);
    }
    @GetMapping
    public ResponseEntity<List<zombieDTO>> readAllZombies() {
        List<zombieDTO> zombies= zombieService.readAll_zombies();
        return ResponseEntity.ok(zombies);
    }
    @GetMapping("/map/{mapId}")
    public ResponseEntity<List<zombieDTO>> readAllZombiesByMapId(@PathVariable("mapId") int mapId) {
        List<zombieDTO> zombies= zombieService.findByID_map(mapId);
        return ResponseEntity.ok(zombies);
    }
    @PutMapping("/{id_zombie}")
    public ResponseEntity<zombieDTO> updateZombie(@PathVariable("id_zombie") int id, @RequestBody zombieDTO zombieDTO) {
        zombieDTO.setId_zombie(id);
        zombieDTO updatedZombie=zombieService.update_zombie(zombieDTO);
        return ResponseEntity.ok(updatedZombie);
    }
    @DeleteMapping("/{id_zombie}")
    public ResponseEntity<zombieDTO> deleteZombie(@PathVariable("id_zombie") int id) {
        zombieService.delete_zombie(id);
        return ResponseEntity.noContent().build();
    }
}
