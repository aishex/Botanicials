package com.botanicials.Botanicials.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String googleId;
    private String imageUrl;
}
