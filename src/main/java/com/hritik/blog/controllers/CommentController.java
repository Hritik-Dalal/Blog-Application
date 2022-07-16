package com.hritik.blog.controllers;

import com.hritik.blog.payloads.ApiResponse;
import com.hritik.blog.payloads.CommentDto;
import com.hritik.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/users/{userId}/posts/{postId}/comments")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto,
                                     @PathVariable("userId") Integer userId,
                                     @PathVariable("postId") Integer postId){
        CommentDto addedComment = this.commentService.addComment(commentDto, postId, userId);
        return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment (@RequestBody CommentDto commentDto,
                         @PathVariable("commentId") Integer commentId){
        CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully!", true), HttpStatus.OK);
    }
}
