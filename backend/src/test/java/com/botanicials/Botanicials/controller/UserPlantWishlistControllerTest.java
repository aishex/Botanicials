package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.config.JwtUtil;
import com.botanicials.Botanicials.dto.UserPlantWishlistDTO;
import com.botanicials.Botanicials.model.UserPlantWishlist;
import com.botanicials.Botanicials.service.UserPlantWishlistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)

@WebMvcTest(UserPlantWishlistController.class)
class UserPlantWishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPlantWishlistService userPlantWishlistService;

    @Autowired
    private ObjectMapper objectMapper;

    private final Long mockUserId = 1L;

    @Test
    void testAddPlantToWishlist() throws Exception {
        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setId(100L);
        wishlist.setPlantId(200L);
        wishlist.setPlantName("Rose");
        wishlist.setImageUrl("http://image.url");

        UserPlantWishlistDTO dto = toDTO(wishlist);

        Mockito.when(userPlantWishlistService.addPlantToWishlist(anyLong(), anyLong(), anyString(), anyString()))
                .thenReturn(wishlist);
        Mockito.when(userPlantWishlistService.convertToDTO(wishlist))
                .thenReturn(dto);

        Map<String, String> body = Map.of(
                "plantId", "200",
                "plantName", "Rose",
                "imageUrl", "http://image.url"
        );

        try (MockedStatic<JwtUtil> mockedJwt = Mockito.mockStatic(JwtUtil.class)) {
            mockedJwt.when(() -> JwtUtil.getUserIdFromRequest(any(HttpServletRequest.class)))
                    .thenReturn(mockUserId);

            mockMvc.perform(post("/wishlist")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(100))
                    .andExpect(jsonPath("$.plantId").value(200))
                    .andExpect(jsonPath("$.plantName").value("Rose"))
                    .andExpect(jsonPath("$.imageUrl").value("http://image.url"));
        }
    }

    @Test
    void testDeleteWishlistPlant() throws Exception {
        Map<String, Long> body = Map.of("plantId", 200L);

        try (MockedStatic<JwtUtil> mockedJwt = Mockito.mockStatic(JwtUtil.class)) {
            mockedJwt.when(() -> JwtUtil.getUserIdFromRequest(any(HttpServletRequest.class)))
                    .thenReturn(mockUserId);

            mockMvc.perform(delete("/wishlist")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)))
                    .andExpect(status().isOk());

            Mockito.verify(userPlantWishlistService).deletePlantFromWishlist(eq(mockUserId), eq(200L));
        }
    }

    @Test
    void testGetWishlistPlants() throws Exception {
        UserPlantWishlist wishlist1 = new UserPlantWishlist();
        wishlist1.setId(1L);
        wishlist1.setPlantId(101L);
        wishlist1.setPlantName("Tulip");
        wishlist1.setImageUrl("url1");

        UserPlantWishlist wishlist2 = new UserPlantWishlist();
        wishlist2.setId(2L);
        wishlist2.setPlantId(102L);
        wishlist2.setPlantName("Sunflower");
        wishlist2.setImageUrl("url2");

        Mockito.when(userPlantWishlistService.getAllWishlistPlantsByUser(mockUserId))
                .thenReturn(List.of(wishlist1, wishlist2));
        Mockito.when(userPlantWishlistService.convertToDTO(wishlist1)).thenReturn(toDTO(wishlist1));
        Mockito.when(userPlantWishlistService.convertToDTO(wishlist2)).thenReturn(toDTO(wishlist2));

        try (MockedStatic<JwtUtil> mockedJwt = Mockito.mockStatic(JwtUtil.class)) {
            mockedJwt.when(() -> JwtUtil.getUserIdFromRequest(any(HttpServletRequest.class)))
                    .thenReturn(mockUserId);

            mockMvc.perform(get("/wishlist"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].plantId").value(101))
                    .andExpect(jsonPath("$[1].plantId").value(102));
        }
    }

    private UserPlantWishlistDTO toDTO(UserPlantWishlist entity) {
        UserPlantWishlistDTO dto = new UserPlantWishlistDTO();
        dto.setId(entity.getId());
        dto.setPlantId(entity.getPlantId());
        dto.setPlantName(entity.getPlantName());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }
}
