package com.blog.react.blogspringreact.repository;

import com.blog.react.blogspringreact.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    Post findPostByIdentifier(String identifier);

}
