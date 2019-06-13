package com.blog.react.blogspringreact.controller;

import com.blog.react.blogspringreact.response.SuccessResponse;
import com.blog.react.blogspringreact.service.PostService;
import com.blog.react.blogspringreact.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {


    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        Iterable posts = postService.findAll();

        return SuccessResponse.response(
                posts,
                Status.GET_ALL,
                ""
        );

    }


}
