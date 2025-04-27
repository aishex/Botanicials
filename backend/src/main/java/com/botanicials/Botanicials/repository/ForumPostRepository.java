package com.botanicials.Botanicials.repository;

import com.botanicials.Botanicials.model.ForumPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPost, Long> {
}