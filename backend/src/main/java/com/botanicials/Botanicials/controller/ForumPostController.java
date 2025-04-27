package com.botanicials.Botanicials.controller;

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
    public ForumPost addForumPost(@RequestBody ForumPost forumPost){
        return forumPostService.addForumPost(forumPost);
    }

    // get all posts
    @GetMapping
    public List<ForumPost> getAllForumPosts(){
        return forumPostService.getAllForumPosts();
    }

    // get post by id
    @GetMapping("/{id}")
    public ForumPost getForumPostById(@PathVariable Long id){
        return forumPostService.getForumPostById(id);
    }

    // update post
    @PutMapping("/{id}")
    public ForumPost updateForumPost(@PathVariable Long id, @RequestBody ForumPost forumPost){
        return forumPostService.updatePost(id, forumPost);
    }

    // delete post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        forumPostService.deleteForumPost(id);
    }
}
