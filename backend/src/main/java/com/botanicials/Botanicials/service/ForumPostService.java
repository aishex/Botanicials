package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.ForumPostDTO;
import com.botanicials.Botanicials.model.ForumPost;
import com.botanicials.Botanicials.repository.ForumPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForumPostService {

    @Autowired
    ForumPostRepository forumPostRepository;

    // save new forum post
    public ForumPost addForumPost(ForumPost forumPost){
        forumPost.setCreatedAt(LocalDateTime.now());
        return forumPostRepository.save(forumPost);
    }

    // get all forum posts
    public List<ForumPost> getAllForumPosts(){
        return forumPostRepository.findAll();
    }

    // get forum post by id
    public ForumPost getForumPostById(Long id){
        return forumPostRepository.findById(id).orElse(null);
    }

    // update forum post
    public ForumPost updatePost(Long id, ForumPost postDetails) {
        ForumPost forumPost = forumPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        forumPost.setTitle(postDetails.getTitle());
        forumPost.setContent(postDetails.getContent());
        forumPost.setImageUrl(postDetails.getImageUrl());

        return forumPostRepository.save(forumPost);
    }

    // delete forum post
    public void deleteForumPost(Long id){
        ForumPost forumPost = forumPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found."));
        forumPostRepository.delete(forumPost);
    }

    // conversion forumpost -> forumpostDTO
    public ForumPostDTO convertToDTO(ForumPost forumPost){
        ForumPostDTO dto = new ForumPostDTO();
        dto.setId(forumPost.getId());
        dto.setUserId(forumPost.getUser().getId());
        dto.setTitle(forumPost.getTitle());
        dto.setContent(forumPost.getContent());
        dto.setImageUrl(forumPost.getImageUrl());
        dto.setCreatedAt(forumPost.getCreatedAt());
        return dto;
    }

    // for list
    public List<ForumPostDTO> getAllForumPostsDTO(){
        List<ForumPost> posts = forumPostRepository.findAll();
        return posts.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
