package com.botanicials.Botanicials.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor

public class UserPlantWishlistDTO {

    private Long id;
    private Long plantId;
    private String imageUrl;
    private String plantName;
}
