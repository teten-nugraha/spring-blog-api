package com.blog.react.blogspringreact.service;

import com.blog.react.blogspringreact.entity.Post;
import com.blog.react.blogspringreact.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post saveOrUpdatePost(Post post) {

        return postRepository.save(post);

    }


    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findByProjectIdentifier(String identifier) {

        Post post = postRepository.findPostByIdentifier(identifier);

        return post;

    }

    public void deleteProject(String identifier){

        Post post = postRepository.findPostByIdentifier(identifier);

        postRepository.delete(post);

    }



}
