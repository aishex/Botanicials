package com.botanicials.Botanicials.repository;

import com.botanicials.Botanicials.model.ForumComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumCommentsRepository extends JpaRepository<ForumComments, Long> {
}