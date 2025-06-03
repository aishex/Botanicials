package com.botanicials.Botanicials.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddCommentDTO {

    private Long forumPostId;
    private Long userId;
    private String content;

}
