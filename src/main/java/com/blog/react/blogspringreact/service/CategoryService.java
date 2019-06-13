package com.blog.react.blogspringreact.service;

import com.blog.react.blogspringreact.entity.Category;
import com.blog.react.blogspringreact.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveOrUpdateCategory(Category category){

        return categoryRepository.save(category);

    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }


}
