package com.botanicials.Botanicials.repository;

import com.botanicials.Botanicials.model.UserPlantCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlantCollectionRepository extends JpaRepository<UserPlantCollection, Long> {
}







