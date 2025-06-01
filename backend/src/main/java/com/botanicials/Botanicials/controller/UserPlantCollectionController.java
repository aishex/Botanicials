package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.config.JwtUtil;
import com.botanicials.Botanicials.dto.UserPlantCollectionDTO;
import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.service.UserPlantCollectionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/collection")
public class UserPlantCollectionController {

    @Autowired
    private UserPlantCollectionService userPlantCollectionService;

    // add new plant to user's collection
    @PostMapping
    public UserPlantCollectionDTO addPlantToCollection(
            @RequestBody Map<String, String> body, HttpServletRequest request){

        Long userId = JwtUtil.getUserIdFromRequest(request);
        Long plantId = Long.valueOf(body.get("plantId"));
        String plantName = body.get("plantName");
        String imageUrl = body.get("imageUrl");

        UserPlantCollection savedPlant = userPlantCollectionService.addPlantToCollection(userId, plantId, plantName, imageUrl);
        return userPlantCollectionService.convertToDTO(savedPlant);
    }

    // delete plant by id
    @DeleteMapping
    public void deletePlantFromCollection(@RequestBody Map<String, Long> body, HttpServletRequest request) {
        Long plantId = body.get("plantId");
        Long userId = JwtUtil.getUserIdFromRequest(request);
        userPlantCollectionService.deletePlantFromCollection(userId, plantId);
    }

    // get user's plant collection
    @GetMapping
    public List<UserPlantCollectionDTO> getPlantCollection(HttpServletRequest request) {
        Long userId = JwtUtil.getUserIdFromRequest(request);
        List<UserPlantCollection> plants = userPlantCollectionService.getAllPlantsByUser(userId);
        return plants.stream()
                .map(userPlantCollectionService::convertToDTO)
                .toList();
    }
}
