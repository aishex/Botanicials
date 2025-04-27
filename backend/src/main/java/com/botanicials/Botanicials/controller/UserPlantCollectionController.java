package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.UserPlantCollectionDTO;
import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.service.UserPlantCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-plant-collection")
public class UserPlantCollectionController {

    @Autowired
    private UserPlantCollectionService userPlantCollectionService;

    // add new plant to user's collection
    @PostMapping
    public UserPlantCollectionDTO addPlantToCollection(@RequestBody UserPlantCollection userPlantCollection){
        UserPlantCollection savedPlant = userPlantCollectionService.addPlantToCollection(userPlantCollection);
        return userPlantCollectionService.convertToDTO(savedPlant);
    }

    // get all plants from all users
    @GetMapping
    public List<UserPlantCollectionDTO> getAllPlants(){
        List<UserPlantCollection> plants = userPlantCollectionService.getAllPlants();
        return plants.stream()
                .map(userPlantCollectionService::convertToDTO)
                .collect(Collectors.toList());
    }

    // get plant by id
    @GetMapping("/{id}")
    public UserPlantCollectionDTO getPlantById(@PathVariable Long id){
        UserPlantCollection plant = userPlantCollectionService.getPlantById(id);
        return userPlantCollectionService.convertToDTO(plant);
    }

    // delete plant by id
    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id){
        userPlantCollectionService.deletePlant(id);
    }
}
