package com.blog.react.blogspringreact.service;

import com.blog.react.blogspringreact.entity.Category;
import com.blog.react.blogspringreact.entity.Post;
import com.blog.react.blogspringreact.exception.PostIdException;
import com.blog.react.blogspringreact.repository.CategoryRepository;
import com.blog.react.blogspringreact.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Post saveOrUpdatePost(String catIdentifier,Post post) {

        Category category = categoryRepository.findByCategoryIdentifier(catIdentifier);

        if(category != null ) {
            post.setCategory(category);
            try{
                return postRepository.save(post);
            }catch (Exception e) {
                throw new PostIdException("Post ID '"+post.getIdentifier()+"' already exists");
            }
        }
        return null;
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
