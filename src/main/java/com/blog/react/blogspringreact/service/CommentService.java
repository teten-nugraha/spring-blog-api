package com.blog.react.blogspringreact.service;

import com.blog.react.blogspringreact.entity.Comment;
import com.blog.react.blogspringreact.exception.ResourceNotFoundException;
import com.blog.react.blogspringreact.repository.CommentRepository;
import com.blog.react.blogspringreact.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Page<Comment> findAll(Long postId, Pageable pageable) {

        return postRepository.findById(postId).map(post -> {
            return commentRepository.findByPostId(postId, pageable);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));


    }

    public Comment createComment(Long postId, Comment comment) {

        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

    }

}
