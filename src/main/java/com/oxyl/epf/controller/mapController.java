package com.oxyl.epf.controller;

import com.oxyl.epf.mapDTO;
import com.oxyl.epf.service.mapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //cette class gère les requêtes http
@RequestMapping("/maps") //tous les endpoints de cette classe commence par /api/maps
public class mapController {
    @Autowired
    private com.oxyl.epf.service.mapService mapService;

    public mapController(mapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping //création
    public ResponseEntity<mapDTO> createMap(@RequestBody mapDTO mapDTO) {
        mapDTO createdMap= mapService.create_map(mapDTO);
        return new ResponseEntity<>(mapDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id_map}")
    public ResponseEntity<mapDTO> readMap(@PathVariable("id_map") int id) {
        mapDTO map=mapService.read_map(id);
        return ResponseEntity.ok(map);
    }

    @GetMapping
    public ResponseEntity<List<mapDTO>> readAllMaps() {
        List<mapDTO> maps=mapService.readAll_maps();
        return ResponseEntity.ok(maps);
    }

    @PutMapping("/{id_map}")
    public ResponseEntity<mapDTO> updateMap(@PathVariable("id_map") int id, @RequestBody mapDTO mapDTO) {
        mapDTO.setId_map(id);
        mapDTO updatedMap=mapService.update_map(mapDTO);
        return ResponseEntity.ok(updatedMap);
    }

    @DeleteMapping("/{id_map}")
    public ResponseEntity<mapDTO> deleteMap(@PathVariable("id_map") int id) {
        mapService.delete_map(id);
        return ResponseEntity.noContent().build();
    }


}
