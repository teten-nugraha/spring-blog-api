package com.blog.react.blogspringreact.controller;

import com.blog.react.blogspringreact.entity.Category;
import com.blog.react.blogspringreact.response.ErrorResponse;
import com.blog.react.blogspringreact.response.SuccessResponse;
import com.blog.react.blogspringreact.service.CategoryService;
import com.blog.react.blogspringreact.service.MapValidationErrorService;
import com.blog.react.blogspringreact.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {

        Iterable<Category> list = categoryService.findAll();

        return SuccessResponse.response(
                list,
                Status.GET_ALL,
                ""
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {

        Category category = categoryService.findById(id);

        return SuccessResponse.response(
            category,
            Status.GET_ONE,
            ""
        );

    }

    @PostMapping("")
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody Category category, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null ) return ErrorResponse.error(errorMap.getBody(), Status.ERROR,errorMap.getStatusCode().toString(), errorMap.getStatusCodeValue());


        Category category1 = categoryService.saveOrUpdateCategory(category);

        return SuccessResponse.response(
                category1,
                Status.CREATED,
                "Berhasil menambahkan kategori baru"
        );

    }

    @DeleteMapping("/{categoryIdentifier}")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryIdentifier) {

        categoryService.deleteCategory(categoryIdentifier);

        return SuccessResponse.response(
            null,
                Status.DELETE,
                "Berhasil menghapus data category"
        );
    }


}
