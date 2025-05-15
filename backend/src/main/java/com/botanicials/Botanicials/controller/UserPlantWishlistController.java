package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.UserPlantWishlistDTO;
import com.botanicials.Botanicials.model.UserPlantWishlist;
import com.botanicials.Botanicials.service.UserPlantWishlistService;
import com.botanicials.Botanicials.config.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-plant-wishlist")
public class UserPlantWishlistController {

    @Autowired
    UserPlantWishlistService userPlantWishlistService;

    // add new plant to wishlist
    @PostMapping
    public UserPlantWishlistDTO addPlantToWishlist(
            @RequestBody Map<String, Long> body, HttpServletRequest request){

            Long plantId = body.get("plantId");
            Long userId = JwtUtil.getUserIdFromRequest(request);
            UserPlantWishlist saved = userPlantWishlistService.addPlantToWishlist(userId, plantId);

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
    @GetMapping("/wishlist/my")
    public List<UserPlantWishlistDTO> getWishlistPlants(HttpServletRequest request){
        Long userId = JwtUtil.getUserIdFromRequest(request);
        List<UserPlantWishlist> plants = userPlantWishlistService.getAllWishlistPlantsByUser(userId);
        return plants.stream()
                .map(userPlantWishlistService::convertToDTO)
                .toList();
    }

}
