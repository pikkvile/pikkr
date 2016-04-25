package com.pikkvile.pikkr.repositories;

import com.pikkvile.pikkr.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {
}
