package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.UserPlantCollectionDTO;
import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.service.UserPlantCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collection")
public class UserPlantCollectionController {

    @Autowired
    private UserPlantCollectionService userPlantCollectionService;

    // add new plant to user's collection
    @PostMapping
    public UserPlantCollectionDTO addPlantToCollection(
            @RequestBody UserPlantCollectionDTO userPlantCollectionDTO,
            @org.springframework.security.core.annotation.AuthenticationPrincipal OAuth2User principal){

        String email = principal.getAttribute("email");
        UserPlantCollection savedPlant = userPlantCollectionService.addPlantToCollection(email, userPlantCollectionDTO);
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

    // get user's plant collection
    @GetMapping("/my")
    public List<UserPlantCollectionDTO> getPlantCollection(@org.springframework.security.core.annotation.AuthenticationPrincipal OAuth2User principal){
        String email = principal.getAttribute("email");
        List<UserPlantCollection> plants = userPlantCollectionService.getAllPlants();
        return plants.stream()
                .filter(p -> p.getUser().getEmail().equals(email))
                .map(userPlantCollectionService::convertToDTO)
                .toList();
    }

}
