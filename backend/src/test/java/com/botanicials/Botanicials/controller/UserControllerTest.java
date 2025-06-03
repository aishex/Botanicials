package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.UserDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User sampleUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setGoogleId("google123");
        user.setImageUrl("http://example.com/image.jpg");
        return user;
    }

    private UserDTO sampleUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setEmail("test@example.com");
        dto.setName("Test User");
        dto.setGoogleId("google123");
        dto.setImageUrl("http://example.com/image.jpg");
        return dto;
    }

    @Test
    void testAddUser() throws Exception {
        User user = sampleUser();
        UserDTO dto = sampleUserDTO();

        Mockito.when(userService.addUser(any(User.class))).thenReturn(user);
        Mockito.when(userService.convertToDTO(user)).thenReturn(dto);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()))
                .andExpect(jsonPath("$.name").value(dto.getName()));
    }

    @Test
    void testGetUserById_found() throws Exception {
        User user = sampleUser();
        UserDTO dto = sampleUserDTO();

        Mockito.when(userService.getUserById(1L)).thenReturn(user);
        Mockito.when(userService.convertToDTO(user)).thenReturn(dto);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dto.getId()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()))
                .andExpect(jsonPath("$.name").value(dto.getName()));
    }

    @Test
    void testGetUserById_notFound() throws Exception {
        Mockito.when(userService.getUserById(1L)).thenReturn(null);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllUsers() throws Exception {
        UserDTO dto1 = sampleUserDTO();
        UserDTO dto2 = new UserDTO();
        dto2.setId(2L);
        dto2.setEmail("other@example.com");
        dto2.setName("Other User");
        dto2.setGoogleId("google456");
        dto2.setImageUrl("http://example.com/image2.jpg");

        List<UserDTO> list = Arrays.asList(dto1, dto2);

        Mockito.when(userService.getAllUsersDTO()).thenReturn(list);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email").value(dto1.getEmail()))
                .andExpect(jsonPath("$[1].name").value(dto2.getName()));
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = sampleUser();
        UserDTO dto = sampleUserDTO();

        Mockito.when(userService.updateUser(eq(1L), any(User.class))).thenReturn(user);
        Mockito.when(userService.convertToDTO(user)).thenReturn(dto);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dto.getName()));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(userService).deleteUser(1L);
    }
}
