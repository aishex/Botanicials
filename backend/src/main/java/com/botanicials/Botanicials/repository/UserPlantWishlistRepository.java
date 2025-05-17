package com.botanicials.Botanicials.repository;

import com.botanicials.Botanicials.model.UserPlantWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPlantWishlistRepository extends JpaRepository<UserPlantWishlist, Long> {
    List<UserPlantWishlist> findByUserId(Long userId);
    Optional<UserPlantWishlist> findByUserIdAndPlantId(Long userId, Long plantId);
}