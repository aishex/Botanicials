package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.config.JwtUtil;
import com.botanicials.Botanicials.dto.UserPlantCollectionDTO;
import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.service.UserPlantCollectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserPlantCollectionController.class)
public class UserPlantCollectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPlantCollectionService userPlantCollectionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Long userId = 1L;
    private final Long plantId = 42L;

    private UserPlantCollection userPlant;

    @BeforeEach
    public void setUp() {
        userPlant = new UserPlantCollection();
        userPlant.setId(1L);
        userPlant.setId(userId);
        userPlant.setPlantId(plantId);
        userPlant.setPlantName("Fikus");
        userPlant.setImageUrl("http://example.com/fikus.jpg");
    }

    @Test
    public void testAddPlantToCollection() throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("plantId", plantId.toString());
        body.put("plantName", "Fikus");
        body.put("imageUrl", "http://example.com/fikus.jpg");

        when(userPlantCollectionService.addPlantToCollection(eq(userId), eq(plantId), eq("Fikus"), eq("http://example.com/fikus.jpg")))
                .thenReturn(userPlant);
        when(userPlantCollectionService.convertToDTO(userPlant))
                .thenReturn(toDTO(userPlant));

        try (MockedStatic<JwtUtil> jwtMock = Mockito.mockStatic(JwtUtil.class)) {
            jwtMock.when(() -> JwtUtil.getUserIdFromRequest(any(HttpServletRequest.class)))
                    .thenReturn(userId);

            mockMvc.perform(post("/collection")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.plantName").value("Fikus"))
                    .andExpect(jsonPath("$.plantId").value(plantId));
        }
    }

    @Test
    public void testGetPlantCollection() throws Exception {
        when(userPlantCollectionService.getAllPlantsByUser(userId))
                .thenReturn(List.of(userPlant));
        when(userPlantCollectionService.convertToDTO(userPlant))
                .thenReturn(toDTO(userPlant));

        try (MockedStatic<JwtUtil> jwtMock = Mockito.mockStatic(JwtUtil.class)) {
            jwtMock.when(() -> JwtUtil.getUserIdFromRequest(any(HttpServletRequest.class)))
                    .thenReturn(userId);

            mockMvc.perform(get("/collection"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].plantName").value("Fikus"));
        }
    }

    @Test
    public void testDeletePlantFromCollection() throws Exception {
        Map<String, Long> body = new HashMap<>();
        body.put("plantId", plantId);

        doNothing().when(userPlantCollectionService).deletePlantFromCollection(userId, plantId);

        try (MockedStatic<JwtUtil> jwtMock = Mockito.mockStatic(JwtUtil.class)) {
            jwtMock.when(() -> JwtUtil.getUserIdFromRequest(any(HttpServletRequest.class)))
                    .thenReturn(userId);

            mockMvc.perform(delete("/collection")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)))
                    .andExpect(status().isOk());
        }
    }

    private UserPlantCollectionDTO toDTO(UserPlantCollection plant) {
        UserPlantCollectionDTO dto = new UserPlantCollectionDTO();
        dto.setId(plant.getId());
        dto.setPlantId(plant.getPlantId());
        dto.setImageUrl(plant.getImageUrl());
        dto.setPlantName(plant.getPlantName());
        return dto;
    }
}
