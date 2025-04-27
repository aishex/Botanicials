package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.UserPlantWishlistDTO;
import com.botanicials.Botanicials.model.UserPlantWishlist;
import com.botanicials.Botanicials.repository.UserPlantWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserPlantWishlistService {

    @Autowired
    UserPlantWishlistRepository userPlantWishlistRepository;

    // save new plant to wishlist
    public UserPlantWishlist addPlantToWishlist(UserPlantWishlist userPlantWishlist){
        return userPlantWishlistRepository.save(userPlantWishlist);
    }

    // get all plants from wishlist
    public List<UserPlantWishlist> getAllWishlistPlants(){
        return userPlantWishlistRepository.findAll();
    }

    // get wishlist plant by id
    public UserPlantWishlist getWishlistPlantById(@PathVariable Long id){
        return userPlantWishlistRepository.findById(id).orElse(null);
    }

    // delete wishlist plant by id
    public void deletePlant(Long id){
        UserPlantWishlist userPlantWishlist = userPlantWishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist plant not found."));
        userPlantWishlistRepository.delete(userPlantWishlist);
    }

    // conversion userplantwishlist -> userplantwishlistDTO
    public UserPlantWishlistDTO convertToDTO(UserPlantWishlist userPlantWishlist){
        UserPlantWishlistDTO dto = new UserPlantWishlistDTO();
        dto.setId(userPlantWishlist.getId());
        dto.setUserId(userPlantWishlist.getUser().getId());
        dto.setPlantId(userPlantWishlist.getPlantId());
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
