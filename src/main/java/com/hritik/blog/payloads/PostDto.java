package com.hritik.blog.payloads;

import com.hritik.blog.entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {

    private int postId;

    private String postTitle;
    private String postContent;

    private Date postCreationDate;
    private String postImageName;

    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comments;

}
