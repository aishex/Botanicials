package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.ForumPostDTO;
import com.botanicials.Botanicials.model.ForumPost;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.repository.ForumPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ForumPostServiceTest {

    @InjectMocks
    private ForumPostService forumPostService;

    @Mock
    private ForumPostRepository forumPostRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddForumPost() {
        ForumPost post = new ForumPost();
        post.setTitle("Title");
        post.setContent("Content");

        ForumPost savedPost = new ForumPost();
        savedPost.setId(1L);
        savedPost.setTitle("Title");
        savedPost.setContent("Content");
        savedPost.setCreatedAt(LocalDateTime.now());

        when(forumPostRepository.save(any(ForumPost.class))).thenReturn(savedPost);

        ForumPost result = forumPostService.addForumPost(post);

        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        verify(forumPostRepository, times(1)).save(any(ForumPost.class));
    }

    @Test
    void testGetAllForumPosts() {
        ForumPost post = new ForumPost();
        post.setId(1L);
        post.setTitle("Test");

        when(forumPostRepository.findAll()).thenReturn(List.of(post));

        List<ForumPost> posts = forumPostService.getAllForumPosts();

        assertEquals(1, posts.size());
        assertEquals("Test", posts.get(0).getTitle());
    }

    @Test
    void testGetForumPostById_Found() {
        ForumPost post = new ForumPost();
        post.setId(1L);

        when(forumPostRepository.findById(1L)).thenReturn(Optional.of(post));

        ForumPost result = forumPostService.getForumPostById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetForumPostById_NotFound() {
        when(forumPostRepository.findById(1L)).thenReturn(Optional.empty());

        ForumPost result = forumPostService.getForumPostById(1L);

        assertNull(result);
    }

    @Test
    void testUpdatePost() {
        ForumPost existing = new ForumPost();
        existing.setId(1L);
        existing.setTitle("Old");

        ForumPost updated = new ForumPost();
        updated.setTitle("New");
        updated.setContent("Updated");
        updated.setImageUrl("url");

        when(forumPostRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(forumPostRepository.save(any())).thenReturn(existing);

        ForumPost result = forumPostService.updatePost(1L, updated);

        assertEquals("New", result.getTitle());
        verify(forumPostRepository).save(existing);
    }

    @Test
    void testDeleteForumPost() {
        ForumPost post = new ForumPost();
        post.setId(1L);

        when(forumPostRepository.findById(1L)).thenReturn(Optional.of(post));

        forumPostService.deleteForumPost(1L);

        verify(forumPostRepository).delete(post);
    }

    @Test
    void testConvertToDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("John");
        user.setImageUrl("img.png");

        ForumPost post = new ForumPost();
        post.setId(2L);
        post.setTitle("T");
        post.setContent("C");
        post.setImageUrl("url");
        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);

        ForumPostDTO dto = forumPostService.convertToDTO(post);

        assertEquals(2L, dto.getId());
        assertEquals("John", dto.getUserName());
    }

    @Test
    void testGetAllForumPostsDTO() {
        ForumPost post = new ForumPost();
        post.setId(1L);
        post.setTitle("T");

        when(forumPostRepository.findAll()).thenReturn(List.of(post));

        List<ForumPostDTO> dtos = forumPostService.getAllForumPostsDTO();

        assertEquals(1, dtos.size());
    }
}
