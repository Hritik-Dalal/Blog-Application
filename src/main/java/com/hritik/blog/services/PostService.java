package com.hritik.blog.services;

import com.hritik.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto getPostByPostId(Integer postId);
    List<PostDto> getAllPosts();
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);

    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> searchPosts(String keyword);
}
