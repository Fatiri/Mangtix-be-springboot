package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.Category;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements com.enigma.services.CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(String id){
        if (!categoryRepository.findById(id).isPresent()){
            throw new NotFoundException(String.format(MessageConstant.ID_CATEGORY_NOT_FOUND,id));
        }
        return categoryRepository.findById(id).get();
    }
    @Override
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    @Override
    public void delete(String id){
        categoryRepository.deleteById(id);
    }
}
