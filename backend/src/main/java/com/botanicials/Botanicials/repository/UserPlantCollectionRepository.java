package com.botanicials.Botanicials.repository;

import com.botanicials.Botanicials.model.UserPlantCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPlantCollectionRepository extends JpaRepository<UserPlantCollection, Long> {
    Optional<UserPlantCollection> findByUserIdAndPlantId(Long userId, Long plantId);
    List<UserPlantCollection> findAllByUserId(Long userId);

}







