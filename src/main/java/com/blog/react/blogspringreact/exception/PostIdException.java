package com.blog.react.blogspringreact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PostIdException extends RuntimeException{

    public PostIdException(String message) {
        super(message);
    }
}
