package com.botanicials.Botanicials.controller;

import com.botanicials.Botanicials.dto.ForumCommentsDTO;
import com.botanicials.Botanicials.model.ForumComments;
import com.botanicials.Botanicials.service.ForumCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class ForumCommentsController {

    @Autowired
    ForumCommentsService forumCommentsService;

    // add new comment
    @PostMapping
    public ResponseEntity<ForumCommentsDTO> addComment(@RequestBody ForumComments forumComments){
        ForumComments savedComment = forumCommentsService.addComment(forumComments);
        return new ResponseEntity<>(forumCommentsService.convertToDTO(savedComment), HttpStatus.CREATED);
    }

    // get all comments
    @GetMapping
    public ResponseEntity<List<ForumCommentsDTO>> getAllComments(){
        List<ForumComments> comments = forumCommentsService.getAllComments();
        List<ForumCommentsDTO> commentDTOs = comments.stream()
                .map(forumCommentsService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }

    // get comment by id
    @GetMapping("/{id}")
    public ResponseEntity<ForumCommentsDTO> getCommentById(@PathVariable Long id){
        ForumComments comment = forumCommentsService.getCommentById(id);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(forumCommentsService.convertToDTO(comment), HttpStatus.OK);
    }

    // get all comments for a specific post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<ForumCommentsDTO>> getCommentsByPostId(@PathVariable Long postId){
        List<ForumComments> comments = forumCommentsService.getCommentsByPostId(postId);
        List<ForumCommentsDTO> commentDTOs = comments.stream()
                .map(forumCommentsService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOs, HttpStatus.OK);
    }

    // update comment
    @PutMapping("/{id}")
    public ResponseEntity<ForumCommentsDTO> updateComment(@PathVariable Long id, @RequestBody ForumComments forumComments){
        ForumComments updatedComment = forumCommentsService.updateComment(id, forumComments);
        return new ResponseEntity<>(forumCommentsService.convertToDTO(updatedComment), HttpStatus.OK);
    }

    // delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        forumCommentsService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
