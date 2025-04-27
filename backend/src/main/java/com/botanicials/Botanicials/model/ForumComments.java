package com.botanicials.Botanicials.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ForumComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ForumPost forumPost;

    @ManyToOne
    private User user;

    private String content;
    private LocalDateTime createdAt;
}
