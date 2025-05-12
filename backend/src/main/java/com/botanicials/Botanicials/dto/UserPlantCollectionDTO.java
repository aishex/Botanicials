package com.botanicials.Botanicials.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserPlantCollectionDTO {

    private Long id;
    private Long plantId;
    private String imageUrl;
    private String plantName;
}
