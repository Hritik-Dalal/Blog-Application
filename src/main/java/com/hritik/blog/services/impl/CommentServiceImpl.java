package com.hritik.blog.services.impl;

import com.hritik.blog.entities.Comment;
import com.hritik.blog.entities.Post;
import com.hritik.blog.entities.User;
import com.hritik.blog.exception.ResourceNotFoundException;
import com.hritik.blog.payloads.CommentDto;
import com.hritik.blog.repositories.CommentRepo;
import com.hritik.blog.repositories.PostRepo;
import com.hritik.blog.repositories.UserRepo;
import com.hritik.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        Comment comment = this.commentDtoToComment(commentDto);
        comment.setUser(user);
        comment.setPost(post);
        Comment addedComment = this.commentRepo.save(comment);
        return this.commentToCommentDto(addedComment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId){
        Comment oldComment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        oldComment.setCommentContent(commentDto.getCommentContent());
        Comment updatedComment = this.commentRepo.save(oldComment);
        return this.commentToCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(Integer commentId) {
            Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
            this.commentRepo.delete(comment);
    }

    private CommentDto commentToCommentDto(Comment comment){
        CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment commentDtoToComment(CommentDto commentDto){
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
