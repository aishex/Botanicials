package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.UserPlantWishlistDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.model.UserPlantWishlist;
import com.botanicials.Botanicials.repository.UserPlantWishlistRepository;
import com.botanicials.Botanicials.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserPlantWishlistService {

    @Autowired
    UserPlantWishlistRepository userPlantWishlistRepository;

    @Autowired
    UserRepository userRepository;

    // save new plant to wishlist
    public UserPlantWishlist addPlantToWishlist(Long userId, Long plantId, String plantName, String imageUrl){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setUser(user);
        wishlist.setPlantId(plantId);
        wishlist.setPlantName(plantName);
        wishlist.setImageUrl(imageUrl);

        return userPlantWishlistRepository.save(wishlist);
    }

    // get all plants from wishlist
    public List<UserPlantWishlist> getAllWishlistPlants(){
        return userPlantWishlistRepository.findAll();
    }

    // get wishlist plant by id
    public UserPlantWishlist getWishlistPlantById(@PathVariable Long id){
        return userPlantWishlistRepository.findById(id).orElse(null);
    }

    // get wishlist plant by user
    public List<UserPlantWishlist> getAllWishlistPlantsByUser(Long userId) {
        return userPlantWishlistRepository.findByUserId(userId);
    }

    // delete wishlist plant by id
    public void deletePlantFromWishlist(Long userId, Long plantId) {
        UserPlantWishlist item = userPlantWishlistRepository.findByUserIdAndPlantId(userId, plantId)
                .orElseThrow(() -> new RuntimeException("Plant not found in wishlist"));
        userPlantWishlistRepository.delete(item);
    }

    // conversion userplantwishlist -> userplantwishlistDTO
    public UserPlantWishlistDTO convertToDTO(UserPlantWishlist wishlist){
        UserPlantWishlistDTO dto = new UserPlantWishlistDTO();
        dto.setId(wishlist.getId());
        dto.setPlantId(wishlist.getPlantId());
        dto.setImageUrl(wishlist.getImageUrl());
        dto.setPlantName(wishlist.getPlantName());
        return dto;
    }

    // for list
    public List<UserPlantWishlistDTO> getAllUserPlantWishlistDTO(){
        List<UserPlantWishlist> wishlist = userPlantWishlistRepository.findAll();
        return wishlist.stream()
                .map(this::convertToDTO)
                .toList();
    }


}
