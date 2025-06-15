package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.model.ForumComments;
import com.botanicials.Botanicials.model.ForumPost;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.repository.ForumCommentsRepository;
import com.botanicials.Botanicials.dto.ForumCommentsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ForumCommentsServiceTest {

    @Mock
    private ForumCommentsRepository forumCommentsRepository;

    @InjectMocks
    private ForumCommentsService forumCommentsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddCommentAndSetCreatedAt() {
        ForumComments input = new ForumComments();
        input.setContent("Test content");

        ForumComments saved = new ForumComments();
        saved.setId(1L);
        saved.setContent("Test content");
        saved.setCreatedAt(LocalDateTime.now());

        when(forumCommentsRepository.save(any(ForumComments.class))).thenReturn(saved);

        ForumComments result = forumCommentsService.addComment(input);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).isEqualTo("Test content");
        assertThat(result.getCreatedAt()).isNotNull();
        verify(forumCommentsRepository, times(1)).save(any());
    }

    @Test
    void shouldReturnAllComments() {
        ForumComments comment1 = new ForumComments();
        ForumComments comment2 = new ForumComments();

        when(forumCommentsRepository.findAll()).thenReturn(List.of(comment1, comment2));

        List<ForumComments> result = forumCommentsService.getAllComments();

        assertThat(result).hasSize(2);
        verify(forumCommentsRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnCommentById() {
        ForumComments comment = new ForumComments();
        comment.setId(1L);
        comment.setContent("Test");

        when(forumCommentsRepository.findById(1L)).thenReturn(Optional.of(comment));

        ForumComments result = forumCommentsService.getCommentById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void shouldReturnNullIfCommentNotFound() {
        when(forumCommentsRepository.findById(99L)).thenReturn(Optional.empty());

        ForumComments result = forumCommentsService.getCommentById(99L);

        assertThat(result).isNull();
    }

    @Test
    void shouldReturnCommentsByPostId() {
        ForumComments comment = new ForumComments();
        comment.setId(1L);

        when(forumCommentsRepository.findByForumPost_Id(5L)).thenReturn(List.of(comment));

        List<ForumComments> result = forumCommentsService.getCommentsByPostId(5L);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }

    @Test
    void shouldUpdateCommentContent() {
        ForumComments existing = new ForumComments();
        existing.setId(1L);
        existing.setContent("Old");

        ForumComments update = new ForumComments();
        update.setContent("New content");

        when(forumCommentsRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(forumCommentsRepository.save(any())).thenReturn(existing);

        ForumComments result = forumCommentsService.updateComment(1L, update);

        assertThat(result.getContent()).isEqualTo("New content");
    }

    @Test
    void shouldDeleteComment() {
        ForumComments comment = new ForumComments();
        comment.setId(1L);

        when(forumCommentsRepository.findById(1L)).thenReturn(Optional.of(comment));

        forumCommentsService.deleteComment(1L);

        verify(forumCommentsRepository, times(1)).delete(comment);
    }

    @Test
    void shouldConvertCommentToDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("Janek");
        user.setImageUrl("img.jpg");

        ForumPost post = new ForumPost();
        post.setId(10L);

        ForumComments comment = new ForumComments();
        comment.setId(5L);
        comment.setUser(user);
        comment.setForumPost(post);
        comment.setContent("Test");
        comment.setCreatedAt(LocalDateTime.now());

        ForumCommentsDTO dto = forumCommentsService.convertToDTO(comment);

        assertThat(dto.getId()).isEqualTo(5L);
        assertThat(dto.getUserId()).isEqualTo(1L);
        assertThat(dto.getForumPostId()).isEqualTo(10L);
        assertThat(dto.getUserName()).isEqualTo("Janek");
        assertThat(dto.getUserImageUrl()).isEqualTo("img.jpg");
    }

    @Test
    void shouldReturnAllCommentsDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("Anna");
        user.setImageUrl("img.jpg");

        ForumPost post = new ForumPost();
        post.setId(2L);

        ForumComments comment = new ForumComments();
        comment.setId(3L);
        comment.setUser(user);
        comment.setForumPost(post);
        comment.setContent("Test");
        comment.setCreatedAt(LocalDateTime.now());

        when(forumCommentsRepository.findAll()).thenReturn(List.of(comment));

        List<ForumCommentsDTO> result = forumCommentsService.getAllCommentsDTO();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserName()).isEqualTo("Anna");
    }
}
