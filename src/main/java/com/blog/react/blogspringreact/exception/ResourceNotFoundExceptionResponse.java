package com.blog.react.blogspringreact.exception;

import lombok.Data;

@Data
public class ResourceNotFoundExceptionResponse {

    private String response;

    public ResourceNotFoundExceptionResponse(String response) {
        this.response = response;
    }
}
