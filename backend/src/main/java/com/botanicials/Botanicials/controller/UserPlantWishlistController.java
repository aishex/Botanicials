package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.UserPlantWishlistDTO;
import com.botanicials.Botanicials.model.UserPlantWishlist;
import com.botanicials.Botanicials.service.UserPlantWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-plant-wishlist")
public class UserPlantWishlistController {

    @Autowired
    UserPlantWishlistService userPlantWishlistService;

    // add new plant to wishlist
    @PostMapping
    public UserPlantWishlistDTO addPlantToWishlist(@RequestBody UserPlantWishlist userPlantWishlist){
        UserPlantWishlist savedPlant = userPlantWishlistService.addPlantToWishlist(userPlantWishlist);
        return userPlantWishlistService.convertToDTO(savedPlant);
    }

    // get all plants from wishlist
    @GetMapping
    public List<UserPlantWishlistDTO> getAllWishlistPlants(){
        List<UserPlantWishlist> plants = userPlantWishlistService.getAllWishlistPlants();
        return plants.stream()
                .map(userPlantWishlistService::convertToDTO)
                .toList();
    }

    // get wishlist plant by id
    @GetMapping("/{id}")
    public UserPlantWishlistDTO getWishlistPlantById(@PathVariable Long id){
        UserPlantWishlist plant = userPlantWishlistService.getWishlistPlantById(id);
        return userPlantWishlistService.convertToDTO(plant);
    }

    // delete wishlist plant by id
    @DeleteMapping("/{id}")
    public void deleteWishListPlant(@PathVariable Long id){
        userPlantWishlistService.deletePlant(id);
    }
}
