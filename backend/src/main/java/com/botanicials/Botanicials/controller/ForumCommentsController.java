package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.ForumCommentsDTO;
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
    public ForumCommentsDTO addComment(@RequestBody ForumComments forumComments){
        ForumComments savedComment = forumCommentsService.addComment(forumComments);
        return forumCommentsService.convertToDTO(savedComment);
    }

    // get all comments
    @GetMapping
    public List<ForumCommentsDTO> getAllComments(){
        List<ForumComments> comments = forumCommentsService.getAllComments();
        return comments.stream()
                .map(forumCommentsService::convertToDTO)
                .toList();
    }

    // get comment by id
    @GetMapping("/{id}")
    public ForumCommentsDTO getCommentById(@PathVariable Long id){
        ForumComments comment = forumCommentsService.getCommentById(id);
        return forumCommentsService.convertToDTO(comment);
    }

    // update comment
    @PutMapping("/{id}")
    public ForumCommentsDTO updateComment(@PathVariable Long id, @RequestBody ForumComments forumComments){
        ForumComments updatedComment = forumCommentsService.updateComment(id, forumComments);
        return forumCommentsService.convertToDTO(updatedComment);
    }

    // delete comment
    @DeleteMapping("/{id}")
    public void DeleteComment(@PathVariable Long id){
        forumCommentsService.deleteComment(id);
    }
}
