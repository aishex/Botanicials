package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.ForumPostDTO;
import com.botanicials.Botanicials.model.ForumPost;
import com.botanicials.Botanicials.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class ForumPostController {

    @Autowired
    ForumPostService forumPostService;

    // add new post
    @PostMapping
    public ForumPostDTO addForumPost(@RequestBody ForumPost forumPost){
        ForumPost savedPost = forumPostService.addForumPost(forumPost);
        return forumPostService.convertToDTO(savedPost);
    }

    // get all posts
    @GetMapping
    public List<ForumPostDTO> getAllForumPosts(){
        List<ForumPost> posts = forumPostService.getAllForumPosts();
        return posts.stream()
                .map(forumPostService::convertToDTO)
                .toList();
    }

    // get post by id
    @GetMapping("/{id}")
    public ForumPostDTO getForumPostById(@PathVariable Long id){
        ForumPost post = forumPostService.getForumPostById(id);
        return forumPostService.convertToDTO(post);
    }

    // update post
    @PutMapping("/{id}")
    public ForumPostDTO updateForumPost(@PathVariable Long id, @RequestBody ForumPost forumPost){
        ForumPost updatedPost = forumPostService.updatePost(id, forumPost);
        return forumPostService.convertToDTO(updatedPost);
    }

    // delete post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        forumPostService.deleteForumPost(id);
    }
}
