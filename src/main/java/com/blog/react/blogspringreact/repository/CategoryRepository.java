package com.blog.react.blogspringreact.repository;

import com.blog.react.blogspringreact.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findByCategoryIdentifier(String catId);

}
