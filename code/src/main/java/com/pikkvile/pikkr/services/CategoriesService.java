package com.pikkvile.pikkr.services;

import com.pikkvile.pikkr.model.entities.Category;
import com.pikkvile.pikkr.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    private static final Sort SORT = new Sort(Sort.Direction.ASC, "position");

    public List<Category> getCategories() {
        return categoriesRepository.findAll(SORT);
    }

    public Category getCategoryById(int categoryId) {
        return categoriesRepository.findOne(categoryId);
    }
}
