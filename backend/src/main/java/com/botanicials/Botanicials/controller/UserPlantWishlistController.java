package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.UserPlantWishlistDTO;
import com.botanicials.Botanicials.model.UserPlantWishlist;
import com.botanicials.Botanicials.service.UserPlantWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-plant-wishlist")
public class UserPlantWishlistController {

    @Autowired
    UserPlantWishlistService userPlantWishlistService;

    // add new plant to wishlist
    @PostMapping
    public UserPlantWishlistDTO addPlantToWishlist(
            @RequestBody UserPlantWishlistDTO dto,
            @org.springframework.security.core.annotation.AuthenticationPrincipal OAuth2User principal){
        String email = principal.getAttribute("email");
        UserPlantWishlist saved = userPlantWishlistService.addPlantToWishlist(email, dto);

        return userPlantWishlistService.convertToDTO(saved);
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

    // get user's wishlist plants
    @GetMapping("/my")
    public List<UserPlantWishlistDTO> getWishlistPlants(@org.springframework.security.core.annotation.AuthenticationPrincipal OAuth2User principal){
        String email = principal.getAttribute("email");
        List<UserPlantWishlist> plants = userPlantWishlistService.getAllWishlistPlants();
        return plants.stream()
                .filter(p -> p.getUser().getEmail().equals(email))
                .map(userPlantWishlistService::convertToDTO)
                .toList();
    }
}
