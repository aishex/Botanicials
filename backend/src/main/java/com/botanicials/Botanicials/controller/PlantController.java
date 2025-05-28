package com.botanicials.Botanicials.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plants")
public class PlantController {

    @Value("${PERENUAL_API}")
    private String token;

    private final RestTemplate restTemplate;

    // fetch all plants or search by name
    @GetMapping
    public ResponseEntity<String> getPlants(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page){

        String url = "https://perenual.com/api/v2/species-list?key=" + token + "&page=" + page;

        if (name != null && !name.isEmpty()) {
            url += "&q=" + name;
        }

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    // get plant by id
    @GetMapping("/{id}")
    public ResponseEntity<String> getPlantById(@PathVariable String id){
        String url = "https://perenual.com/api/v2/species/details/" + id + "?key=" + token;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
