package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.ForumPostDTO;
import com.botanicials.Botanicials.model.ForumPost;
import com.botanicials.Botanicials.service.ForumPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(ForumPostController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ForumPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForumPostService forumPostService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddForumPost() throws Exception {
        ForumPost post = new ForumPost();
        post.setTitle("Test Post");
        post.setContent("Test content");

        ForumPostDTO dto = new ForumPostDTO();
        dto.setId(1L);
        dto.setTitle("Test Post");
        dto.setContent("Test content");

        Mockito.when(forumPostService.addForumPost(any())).thenReturn(post);
        Mockito.when(forumPostService.convertToDTO(any())).thenReturn(dto);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Post"));
    }

    @Test
    void testGetAllForumPosts() throws Exception {
        ForumPost post = new ForumPost();
        post.setId(1L);
        post.setTitle("Post 1");
        post.setContent("Content");

        ForumPostDTO dto = new ForumPostDTO();
        dto.setId(1L);
        dto.setTitle("Post 1");

        Mockito.when(forumPostService.getAllForumPosts()).thenReturn(List.of(post));
        Mockito.when(forumPostService.convertToDTO(post)).thenReturn(dto);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Post 1"));
    }

    @Test
    void testGetForumPostById_Found() throws Exception {
        ForumPost post = new ForumPost();
        post.setId(1L);
        post.setTitle("Test");

        ForumPostDTO dto = new ForumPostDTO();
        dto.setId(1L);
        dto.setTitle("Test");

        Mockito.when(forumPostService.getForumPostById(1L)).thenReturn(post);
        Mockito.when(forumPostService.convertToDTO(post)).thenReturn(dto);

        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test"));
    }

    @Test
    void testGetForumPostById_NotFound() throws Exception {
        Mockito.when(forumPostService.getForumPostById(999L)).thenReturn(null);

        mockMvc.perform(get("/posts/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateForumPost() throws Exception {
        ForumPost post = new ForumPost();
        post.setId(1L);
        post.setTitle("Updated");

        ForumPostDTO dto = new ForumPostDTO();
        dto.setId(1L);
        dto.setTitle("Updated");

        Mockito.when(forumPostService.updatePost(eq(1L), any())).thenReturn(post);
        Mockito.when(forumPostService.convertToDTO(post)).thenReturn(dto);

        mockMvc.perform(put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"));
    }

    @Test
    void testDeleteForumPost() throws Exception {
        mockMvc.perform(delete("/posts/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(forumPostService).deleteForumPost(1L);
    }
}
