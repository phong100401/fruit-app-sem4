package com.example.restapiprojectsem4.service;

import com.example.restapiprojectsem4.repository.CategoryRepository;
import com.example.restapiprojectsem4.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllByStatus() {
        return categoryRepository.findAllByStatus();
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category updated(int id, Category category) {
        Category exist = categoryRepository.getById(id);
        if(exist == null){
            return null;
        }
        exist.setName(category.getName());
        exist.setStatus(category.getStatus());
        return categoryRepository.save(exist);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category deleted(int id) {
        Category deleted = categoryRepository.getById(id);
        if(deleted == null){
            return null;
        }
        deleted.setStatus("DELETED");
        return categoryRepository.save(deleted);
    }
}
