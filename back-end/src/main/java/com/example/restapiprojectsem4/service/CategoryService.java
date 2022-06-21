package com.example.restapiprojectsem4.service;

import com.example.restapiprojectsem4.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllByStatus();
    Optional<Category> findById(int id);
    Category updated(int id, Category category);
    List<Category> findByName(String name);
    Category create(Category category);
    Category deleted(int id);
}
