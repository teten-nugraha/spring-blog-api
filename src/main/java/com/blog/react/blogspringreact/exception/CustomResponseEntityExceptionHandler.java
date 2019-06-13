package com.blog.react.blogspringreact.exception;

import com.blog.react.blogspringreact.response.ErrorResponse;
import com.blog.react.blogspringreact.util.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handleCategoryIdAlreadyExistsException(CategoryIdException ex, WebRequest request) {

        CategoryIdExceptionResponse exceptionResponse = new CategoryIdExceptionResponse(ex.getMessage());

        return ErrorResponse.error(exceptionResponse, Status.ERROR,null, null);


    }
}
