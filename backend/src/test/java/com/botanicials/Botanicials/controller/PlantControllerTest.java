package com.botanicials.Botanicials.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlantController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PlantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetPlants_withoutName() throws Exception {
        // Arrange
        String fakeResponse = "{\"data\": []}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(fakeResponse, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(
                        ArgumentMatchers.contains("species-list"),
                        ArgumentMatchers.eq(String.class)))
                .thenReturn(responseEntity);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/plants?page=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(fakeResponse));
    }

    @Test
    void testGetPlants_withName() throws Exception {
        // Arrange
        String fakeResponse = "{\"data\": [{\"common_name\": \"Rose\"}]}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(fakeResponse, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(
                        ArgumentMatchers.contains("species-list"),
                        ArgumentMatchers.eq(String.class)))
                .thenReturn(responseEntity);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/plants?name=rose&page=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(fakeResponse));
    }

    @Test
    void testGetPlantById() throws Exception {
        // Arrange
        String fakeResponse = "{\"common_name\": \"Rose\"}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(fakeResponse, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(
                        ArgumentMatchers.contains("species/details/123"),
                        ArgumentMatchers.eq(String.class)))
                .thenReturn(responseEntity);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/plants/123"))
                .andExpect(status().isOk())
                .andExpect(content().json(fakeResponse));
    }
}
