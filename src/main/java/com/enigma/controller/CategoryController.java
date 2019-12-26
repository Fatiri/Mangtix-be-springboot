package com.enigma.controller;

import com.enigma.entity.Category;
import com.enigma.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @CrossOrigin
    @PostMapping("/category")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @CrossOrigin
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }

    @CrossOrigin
    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @CrossOrigin
    @DeleteMapping("/category/{id}")
    public void deleteById(@PathVariable String id){
        categoryService.delete(id);
    }
}
