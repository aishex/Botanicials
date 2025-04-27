package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.service.UserPlantCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-plant-collection")
public class UserPlantCollectionController {

    @Autowired
    private UserPlantCollectionService userPlantCollectionService;

    // add new plant to user's collection
    @PostMapping
    public UserPlantCollection addPlantToCollection(@RequestBody UserPlantCollection userPlantCollection){
        return userPlantCollectionService.addPlantToCollection(userPlantCollection);
    }

    // get all plants from all users
    @GetMapping
    public List<UserPlantCollection> getAllPlants(){
        return userPlantCollectionService.getAllPlants();
    }

    // get plant by id
    @GetMapping("/{id}")
    public UserPlantCollection getPlantById(@PathVariable Long id){
        return userPlantCollectionService.getPlantById(id);
    }

    // delete plant by id
    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id){
        userPlantCollectionService.deletePlant(id);
    }
}
