package com.hritik.blog.services;

import com.hritik.blog.payloads.CommentDto;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto, Integer postId, Integer userId);
    CommentDto updateComment(CommentDto commentDto, Integer commentId);
    void deleteComment(Integer commentId);
}
