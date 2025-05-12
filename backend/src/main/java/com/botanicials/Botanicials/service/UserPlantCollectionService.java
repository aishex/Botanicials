package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.UserPlantCollectionDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.repository.UserPlantCollectionRepository;
import com.botanicials.Botanicials.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPlantCollectionService {

    @Autowired
    UserPlantCollectionRepository userPlantCollectionRepository;

    @Autowired
    UserRepository userRepository;

    // add a plant to user's collection
    public UserPlantCollection addPlantToCollection(String userEmail, UserPlantCollectionDTO dto){
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found."));
        UserPlantCollection plant = new UserPlantCollection();
        plant.setUser(user);
        plant.setPlantId(dto.getPlantId());
        plant.setPlantName(dto.getPlantName());
        plant.setImageUrl(dto.getImageUrl());

        return userPlantCollectionRepository.save(plant);
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

    // conversion userplantcollection -> userplancollectionDTO
    public UserPlantCollectionDTO convertToDTO(UserPlantCollection userPlantCollection){
        UserPlantCollectionDTO dto = new UserPlantCollectionDTO();
        dto.setId(userPlantCollection.getId());
        dto.setPlantId(userPlantCollection.getPlantId());
        dto.setPlantName(userPlantCollection.getPlantName());
        dto.setImageUrl(userPlantCollection.getImageUrl());
        return dto;
    }

    // for list
    public List<UserPlantCollectionDTO> getAllUserPlantCollectionsDTO(){
        List<UserPlantCollection> collections = userPlantCollectionRepository.findAll();
        return collections.stream()
                .map(this::convertToDTO)
                .toList();
    }

}
