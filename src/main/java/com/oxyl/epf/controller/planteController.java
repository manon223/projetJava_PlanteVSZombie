package com.oxyl.epf.controller;

import com.oxyl.epf.planteDTO;
import com.oxyl.epf.service.planteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantes")
public class planteController {
    @Autowired
    private final com.oxyl.epf.service.planteService planteService;
    public planteController(planteService planteService) {
        this.planteService = planteService;
    }
    @PostMapping
    public ResponseEntity<planteDTO> createPlante(@RequestBody planteDTO planteDTO) {
        planteDTO createdPlante=planteService.create_plante(planteDTO);
        return new ResponseEntity<>(createdPlante, HttpStatus.CREATED);

    }
    @GetMapping("/{id_plante}")
    public ResponseEntity<planteDTO> readPlante(@PathVariable("id_plante") int id) {
        planteDTO plante=planteService.read_plante(id);
        return ResponseEntity.ok(plante);
    }
    @GetMapping
    public ResponseEntity<List<planteDTO>> readAllPlantes() {
        List<planteDTO> plantes=planteService.readAll_plantes();
        return ResponseEntity.ok(plantes);
    }
    @PutMapping("/{id_plante}")
    public ResponseEntity<planteDTO> updatePlante(@PathVariable("id_plante") int id, @RequestBody planteDTO planteDTO) {
        planteDTO.setId_plante(id);
        planteDTO updatedPlante=planteService.update_plante(planteDTO);
        return ResponseEntity.ok(updatedPlante);
    }
    @DeleteMapping("/{id_plante}")
    public ResponseEntity<planteDTO> deletePlante(@PathVariable("id_plante") int id) {
        planteService.delete_plante(id);
        return ResponseEntity.noContent().build();
    }

}
