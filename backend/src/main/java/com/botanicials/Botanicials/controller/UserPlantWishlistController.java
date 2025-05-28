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
@RequestMapping("/wishlist")
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

    // delete wishlist plant by id
    @DeleteMapping
    public void deleteWishlistPlant(@RequestBody Map<String, Long> body, HttpServletRequest request) {
        Long plantId = body.get("plantId");
        Long userId = JwtUtil.getUserIdFromRequest(request);
        userPlantWishlistService.deletePlantFromWishlist(userId, plantId);
    }

    // get user's wishlist plants
    @GetMapping
    public List<UserPlantWishlistDTO> getWishlistPlants(HttpServletRequest request) {
        Long userId = JwtUtil.getUserIdFromRequest(request);
        List<UserPlantWishlist> plants = userPlantWishlistService.getAllWishlistPlantsByUser(userId);
        return plants.stream()
                .map(userPlantWishlistService::convertToDTO)
                .toList();
    }

}
