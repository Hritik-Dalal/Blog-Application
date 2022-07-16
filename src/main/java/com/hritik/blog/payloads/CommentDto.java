package com.hritik.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentDto {

    private int commentId;

    private String commentContent;

    private UserDto user;
//    private PostDto post;
}
