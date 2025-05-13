package com.botanicials.Botanicials.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ForumCommentsDTO {
    private Long id;
    private Long forumPostId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
}