package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.ForumPostDTO;
import com.botanicials.Botanicials.model.ForumPost;
import com.botanicials.Botanicials.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class ForumPostController {

    @Autowired
    ForumPostService forumPostService;

    // add new post
    @PostMapping
    public ResponseEntity<ForumPostDTO> addForumPost(@RequestBody ForumPost forumPost){
        ForumPost savedPost = forumPostService.addForumPost(forumPost);
        return new ResponseEntity<>(forumPostService.convertToDTO(savedPost), HttpStatus.CREATED);
    }

    // get all posts
    @GetMapping
    public ResponseEntity<List<ForumPostDTO>> getAllForumPosts(){
        List<ForumPost> posts = forumPostService.getAllForumPosts();
        List<ForumPostDTO> postDTOs = posts.stream()
                .map(forumPostService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDTOs, HttpStatus.OK);
    }

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<ForumPostDTO> getForumPostById(@PathVariable Long id){
        ForumPost post = forumPostService.getForumPostById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(forumPostService.convertToDTO(post), HttpStatus.OK);
    }

    // update post
    @PutMapping("/{id}")
    public ResponseEntity<ForumPostDTO> updateForumPost(@PathVariable Long id, @RequestBody ForumPost forumPost){
        ForumPost updatedPost = forumPostService.updatePost(id, forumPost);
        return new ResponseEntity<>(forumPostService.convertToDTO(updatedPost), HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        forumPostService.deleteForumPost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
