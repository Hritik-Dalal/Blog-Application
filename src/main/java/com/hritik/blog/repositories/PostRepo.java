package com.hritik.blog.repositories;

import com.hritik.blog.entities.Category;
import com.hritik.blog.entities.Post;
import com.hritik.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
