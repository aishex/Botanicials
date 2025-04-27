package com.botanicials.Botanicials.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String googleId;
    private String imageUrl;
}
