package com.botanicials.Botanicials.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ForumPostDTO {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
