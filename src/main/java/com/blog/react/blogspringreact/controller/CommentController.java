package com.blog.react.blogspringreact.controller;

import com.blog.react.blogspringreact.entity.Comment;
import com.blog.react.blogspringreact.response.ErrorResponse;
import com.blog.react.blogspringreact.response.SuccessResponse;
import com.blog.react.blogspringreact.service.CommentService;
import com.blog.react.blogspringreact.service.MapValidationErrorService;
import com.blog.react.blogspringreact.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> createOrUpdate(@PathVariable("postId")Long postId, @Valid @RequestBody Comment comment, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null ) return ErrorResponse.error(errorMap.getBody(), Status.ERROR,errorMap.getStatusCode().toString(), errorMap.getStatusCodeValue());

        Comment comment1 = commentService.createComment(postId, comment);

        return SuccessResponse.response(
                comment1,
                Status.CREATED,
                "Berhasil membuat post baru"
        );


    }

}
