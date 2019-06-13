package com.blog.react.blogspringreact.service;

import com.blog.react.blogspringreact.entity.Category;
import com.blog.react.blogspringreact.exception.CategoryIdException;
import com.blog.react.blogspringreact.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveOrUpdateCategory(Category category){

        try{
            return categoryRepository.save(category);
        }catch (Exception e) {
            throw new CategoryIdException("Project ID '"+category.getCategoryIdentifier()+"' already exists");
        }

    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findByCategoryIdentifier(String identifier) {

        Category categoryIdentifier = categoryRepository.findByCategoryIdentifier(identifier);

        return categoryIdentifier;
    }

    public void deleteCategory(String identifier) {

        categoryRepository.delete(findByCategoryIdentifier(identifier));

    }


}
