package com.botanicials.Botanicials.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ForumPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
}
