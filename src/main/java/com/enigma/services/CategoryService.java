package com.enigma.services;

import com.enigma.entity.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);

    Category getCategoryById(String id);

    List<Category> getAllCategory();

    void delete(String id);
}
