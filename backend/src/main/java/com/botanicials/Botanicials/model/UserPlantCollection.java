package com.botanicials.Botanicials.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter @Setter
public class UserPlantCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Long plantId;
    private String imageUrl;
    private String plantName;
}
