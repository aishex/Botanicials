package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.model.ForumComments;
import com.botanicials.Botanicials.service.ForumCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class ForumCommentsController {

    @Autowired
    ForumCommentsService forumCommentsService;

    // add new comment
    @PostMapping
    public ForumComments addComment(@RequestBody ForumComments forumComments){
        return forumCommentsService.addComment(forumComments);
    }

    // get all comments
    @GetMapping
    public List<ForumComments> getAllComments(){
        return forumCommentsService.getAllComments();
    }

    // get comment by id
    @GetMapping("/{id}")
    public ForumComments getCommentById(@PathVariable Long id){
        return forumCommentsService.getCommentById(id);
    }

    // update comment
    @PutMapping("/{id}")
    public ForumComments updateComment(@PathVariable Long id, @RequestBody ForumComments forumComments){
        return forumCommentsService.updateComment(id, forumComments);
    }

    // delete comment
    @DeleteMapping("/{id}")
    public void DeleteComment(@PathVariable Long id){
        forumCommentsService.deleteComment(id);
    }
}
