package com.botanicials.Botanicials.controller;

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
    public UserPlantWishlist addPlantToWishlist(@RequestBody UserPlantWishlist userPlantWishlist){
        return userPlantWishlistService.addPlantToWishlist(userPlantWishlist);
    }

    // get all plants from wishlist
    @GetMapping
    public List<UserPlantWishlist> getAllWishlistPlants(){
        return userPlantWishlistService.getAllWishlistPlants();
    }

    // get wishlist plant by id
    @GetMapping("/{id}")
    public UserPlantWishlist getWishlistPlantById(@PathVariable Long id){
        return userPlantWishlistService.getWishlistPlantById(id);
    }

    // delete wishlist plant by id
    @DeleteMapping("/{id}")
    public void deleteWishListPlant(@PathVariable Long id){
        userPlantWishlistService.deletePlant(id);
    }
}
