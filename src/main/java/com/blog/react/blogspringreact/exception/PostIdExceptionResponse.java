package com.blog.react.blogspringreact.exception;

import lombok.Data;

@Data
public class PostIdExceptionResponse {

    private String postIdentifier;

    public PostIdExceptionResponse(String postIdentifier) {
        this.postIdentifier = postIdentifier;
    }
}
