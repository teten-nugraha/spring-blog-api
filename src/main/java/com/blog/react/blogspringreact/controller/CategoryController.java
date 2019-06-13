package com.blog.react.blogspringreact.controller;

import com.blog.react.blogspringreact.entity.Category;
import com.blog.react.blogspringreact.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody Category category, BindingResult result) {

        Category category1 = categoryService.saveOrUpdateCategory(category);

        return new ResponseEntity<Category>(category1, HttpStatus.CREATED);

    }


}
