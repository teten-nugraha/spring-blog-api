package com.blog.react.blogspringreact.controller;

import com.blog.react.blogspringreact.entity.Post;
import com.blog.react.blogspringreact.response.ErrorResponse;
import com.blog.react.blogspringreact.response.SuccessResponse;
import com.blog.react.blogspringreact.service.MapValidationErrorService;
import com.blog.react.blogspringreact.service.PostService;
import com.blog.react.blogspringreact.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        Iterable posts = postService.findAll();

        return SuccessResponse.response(
                posts,
                Status.GET_ALL,
                ""
        );

    }


    @PostMapping("/{catIdentifier}")
    public ResponseEntity<?> saveOrUpdate(@PathVariable(value="catIdentifier")String catIdentifier, @Valid @RequestBody  Post post, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null ) return ErrorResponse.error(errorMap.getBody(), Status.ERROR,errorMap.getStatusCode().toString(), errorMap.getStatusCodeValue());

        Post post1 = postService.saveOrUpdatePost(catIdentifier,post);

        return SuccessResponse.response(
                post1,
            Status.CREATED,
            "Behasil membuat post baru"
        );

    }

}
