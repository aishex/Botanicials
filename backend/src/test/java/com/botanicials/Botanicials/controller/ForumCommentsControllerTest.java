package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.AddCommentDTO;
import com.botanicials.Botanicials.dto.ForumCommentsDTO;
import com.botanicials.Botanicials.model.ForumComments;
import com.botanicials.Botanicials.model.ForumPost;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.repository.ForumPostRepository;
import com.botanicials.Botanicials.repository.UserRepository;
import com.botanicials.Botanicials.service.ForumCommentsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc(addFilters = false)

@WebMvcTest(ForumCommentsController.class)
public class ForumCommentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForumCommentsService forumCommentsService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ForumPostRepository forumPostRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddComment() throws Exception {
        AddCommentDTO dto = new AddCommentDTO();
        dto.setContent("Nice post!");
        dto.setUserId(1L);
        dto.setForumPostId(2L);

        User user = new User();
        user.setId(1L);
        user.setName("Jan");
        user.setImageUrl("img.jpg");

        ForumPost post = new ForumPost();
        post.setId(2L);

        ForumComments comment = new ForumComments();
        comment.setId(10L);
        comment.setContent(dto.getContent());
        comment.setUser(user);
        comment.setForumPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        ForumCommentsDTO responseDto = new ForumCommentsDTO();
        responseDto.setId(10L);
        responseDto.setUserId(1L);
        responseDto.setForumPostId(2L);
        responseDto.setUserName("Jan");
        responseDto.setUserImageUrl("img.jpg");
        responseDto.setContent("Nice post!");
        responseDto.setCreatedAt(comment.getCreatedAt());

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(forumPostRepository.findById(2L)).thenReturn(Optional.of(post));
        Mockito.when(forumCommentsService.addComment(any())).thenReturn(comment);
        Mockito.when(forumCommentsService.convertToDTO(any())).thenReturn(responseDto);

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    void testGetAllComments() throws Exception {
        ForumCommentsDTO dto = new ForumCommentsDTO();
        dto.setId(1L);
        dto.setContent("Test");

        Mockito.when(forumCommentsService.getAllComments()).thenReturn(List.of(new ForumComments()));
        Mockito.when(forumCommentsService.convertToDTO(any())).thenReturn(dto);

        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void testGetCommentByIdFound() throws Exception {
        ForumComments comment = new ForumComments();
        comment.setId(1L);
        comment.setContent("Comment");

        ForumCommentsDTO dto = new ForumCommentsDTO();
        dto.setId(1L);
        dto.setContent("Comment");

        Mockito.when(forumCommentsService.getCommentById(1L)).thenReturn(comment);
        Mockito.when(forumCommentsService.convertToDTO(comment)).thenReturn(dto);

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testGetCommentByIdNotFound() throws Exception {
        Mockito.when(forumCommentsService.getCommentById(1L)).thenReturn(null);

        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetCommentsByPostId() throws Exception {
        ForumComments comment = new ForumComments();
        comment.setId(1L);

        ForumCommentsDTO dto = new ForumCommentsDTO();
        dto.setId(1L);
        dto.setContent("Test");

        Mockito.when(forumCommentsService.getCommentsByPostId(10L)).thenReturn(List.of(comment));
        Mockito.when(forumCommentsService.convertToDTO(comment)).thenReturn(dto);

        mockMvc.perform(get("/comments/post/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void testUpdateComment() throws Exception {
        ForumComments updated = new ForumComments();
        updated.setId(1L);
        updated.setContent("Updated");

        ForumCommentsDTO dto = new ForumCommentsDTO();
        dto.setId(1L);
        dto.setContent("Updated");

        Mockito.when(forumCommentsService.updateComment(any(), any())).thenReturn(updated);
        Mockito.when(forumCommentsService.convertToDTO(updated)).thenReturn(dto);

        mockMvc.perform(put("/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated"));
    }

    @Test
    void testDeleteComment() throws Exception {
        mockMvc.perform(delete("/comments/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(forumCommentsService).deleteComment(1L);
    }
}
