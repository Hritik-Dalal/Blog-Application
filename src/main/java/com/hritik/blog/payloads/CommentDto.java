package com.hritik.blog.payloads;

import com.hritik.blog.entities.Post;
import com.hritik.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentDto {

    private int commentId;

    private String commentContent;

//    private User user;
//    private Post post;
}
