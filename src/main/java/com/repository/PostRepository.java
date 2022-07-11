package com.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    @Transactional
    List<Post> findAllByOrderByIdDesc();
}
