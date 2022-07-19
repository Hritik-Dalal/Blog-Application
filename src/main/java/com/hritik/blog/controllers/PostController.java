package com.hritik.blog.controllers;

import com.hritik.blog.config.AppConstants;
import com.hritik.blog.payloads.ApiResponse;
import com.hritik.blog.payloads.PostDto;
import com.hritik.blog.payloads.PostResponse;
import com.hritik.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Integer userId,
                                              @PathVariable("categoryId") Integer categoryId)
    {

        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
        List<PostDto> listOfAllPostDtosWithSameUser = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(listOfAllPostDtosWithSameUser, HttpStatus.FOUND);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId){
        List<PostDto> listOfAllPostDtosWithSameCategory = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(listOfAllPostDtosWithSameCategory, HttpStatus.FOUND);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(

            //Page number starts with 0
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDirection", defaultValue = AppConstants.SORT_DIR, required = false) String sortDirection){

        PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(postResponse, HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostByPostId(postId);
        return new ResponseEntity<>(postDto, HttpStatus.FOUND);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> listOfAllPosts = this.postService.searchPosts(keywords);
        return new ResponseEntity<>(listOfAllPosts, HttpStatus.FOUND);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostByPostId(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostByPostId(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully!", true), HttpStatus.OK);
    }
}
