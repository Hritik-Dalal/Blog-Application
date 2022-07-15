package com.hritik.blog.services.impl;

import com.hritik.blog.entities.Category;
import com.hritik.blog.entities.Post;
import com.hritik.blog.entities.User;
import com.hritik.blog.exception.ResourceNotFoundException;
import com.hritik.blog.payloads.PostDto;
import com.hritik.blog.repositories.CategoryRepo;
import com.hritik.blog.repositories.PostRepo;
import com.hritik.blog.repositories.UserRepo;
import com.hritik.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = this.postDtoToPost(postDto);
        post.setUser(user);
        post.setCategory(category);
        post.setPostImageName("default.png");
        post.setPostCreationDate(new Date());
        Post addedPost = this.postRepo.save(post);
        return this.postToPostDto(addedPost);
    }

    @Override
    public PostDto getPostByPostId(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        return this.postToPostDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        if(!this.postRepo.findAll().isEmpty()){
            List<Post> listOfAllPosts = this.postRepo.findAll();
            List<PostDto> listOfAllPostDto = listOfAllPosts.stream().map(post -> this.postToPostDto(post)).collect(Collectors.toList());
            return listOfAllPostDto;
        }else {
            throw new ResourceNotFoundException("No post found in the database!");
        }
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post oldPost = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        oldPost.setPostTitle(postDto.getPostTitle());
        oldPost.setPostContent(postDto.getPostContent());
        Post updatedPost = this.postRepo.save(oldPost);
        return this.postToPostDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post postToBeDeleted = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        this.postRepo.delete(postToBeDeleted);
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }

    public Post postDtoToPost(PostDto postDto){
        Post post = this.modelMapper.map(postDto, Post.class);
        return post;
    }

    public PostDto postToPostDto(Post post){
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }
}