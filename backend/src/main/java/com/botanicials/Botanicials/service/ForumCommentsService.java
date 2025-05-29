package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.ForumCommentsDTO;
import com.botanicials.Botanicials.model.ForumComments;
import com.botanicials.Botanicials.repository.ForumCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ForumCommentsService {

    @Autowired
    ForumCommentsRepository forumCommentsRepository;

    // save new comment
    public ForumComments addComment(ForumComments forumComments){
        forumComments.setCreatedAt(LocalDateTime.now());
        return forumCommentsRepository.save(forumComments);
    }

    // get all comments
    public List<ForumComments> getAllComments(){
        return forumCommentsRepository.findAll();
    }

    // get comment by id
    public ForumComments getCommentById(Long id){
        return forumCommentsRepository.findById(id).orElse(null);
    }

    // update comment
    public ForumComments updateComment(Long id, ForumComments forumCommentsDetails){
        ForumComments comment = forumCommentsRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(forumCommentsDetails.getContent());
        return forumCommentsRepository.save(comment);
    }

    // delete comment
    public void deleteComment(Long id){
        ForumComments comment = forumCommentsRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        forumCommentsRepository.delete(comment);
    }

    // conversion forumcomment -> forumcommentDTO
    public ForumCommentsDTO convertToDTO(ForumComments forumComments){
        ForumCommentsDTO dto = new ForumCommentsDTO();
        dto.setId(forumComments.getId());
        dto.setForumPostId(forumComments.getForumPost().getId());
        dto.setUserId(forumComments.getUser().getId());
        dto.setContent(forumComments.getContent());
        dto.setCreatedAt(forumComments.getCreatedAt());
        return dto;
    }

    // for list
    public List<ForumCommentsDTO> getAllCommentsDTO(){
        List<ForumComments> comments = forumCommentsRepository.findAll();
        return comments.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
