package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.repository.UserPlantCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlantCollectionService {

    @Autowired
    UserPlantCollectionRepository userPlantCollectionRepository;

    // add a plant to user's collection
    public UserPlantCollection addPlantToCollection(UserPlantCollection userPlantCollection){
        return userPlantCollectionRepository.save(userPlantCollection);
    }

    // get all plants from user's collection
    public List<UserPlantCollection> getAllPlants(){
        return userPlantCollectionRepository.findAll();
    }

    // get plant collection by id
    public UserPlantCollection getPlantById(Long id){
        return userPlantCollectionRepository.findById(id).orElse(null);
    }

    // remove plant from collection by id
    public void deletePlant(Long id){
        UserPlantCollection plant = userPlantCollectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found."));
        userPlantCollectionRepository.delete(plant);
    }

}
