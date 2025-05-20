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
    public UserPlantCollection addPlantToCollection(Long userId, Long plantId, String plantName, String imageUrl) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        UserPlantCollection collection = new UserPlantCollection();
        collection.setUser(user);
        collection.setPlantId(plantId);
        collection.setPlantName(plantName);
        collection.setImageUrl(imageUrl);
        return userPlantCollectionRepository.save(collection);
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

    // delete plant from user's collection
    public void deletePlantFromCollection(Long userId, Long plantId) {
        var plant = userPlantCollectionRepository.findByUserIdAndPlantId(userId, plantId)
                .orElseThrow(() -> new RuntimeException("plant not found in user's collection"));
        userPlantCollectionRepository.delete(plant);
    }

    // get all user's plants
    public List<UserPlantCollection> getAllPlantsByUser(Long userId) {
        return userPlantCollectionRepository.findAllByUserId(userId);
    }
}
