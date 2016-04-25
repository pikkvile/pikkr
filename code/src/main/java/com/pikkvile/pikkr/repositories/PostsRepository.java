package com.pikkvile.pikkr.repositories;

import com.pikkvile.pikkr.model.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByCategoryTitle(String categoryTitle, Pageable pageable);
}
