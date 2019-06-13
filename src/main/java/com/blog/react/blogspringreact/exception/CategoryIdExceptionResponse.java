package com.blog.react.blogspringreact.exception;

import lombok.Data;

@Data
public class CategoryIdExceptionResponse {


    private String categoryIdentifier;

    public CategoryIdExceptionResponse(String categoryIdentifier) {
        this.categoryIdentifier = categoryIdentifier;
    }
}
