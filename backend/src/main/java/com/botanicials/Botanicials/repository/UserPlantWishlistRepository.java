package com.botanicials.Botanicials.repository;

import com.botanicials.Botanicials.model.UserPlantWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlantWishlistRepository extends JpaRepository<UserPlantWishlist, Long> {
}