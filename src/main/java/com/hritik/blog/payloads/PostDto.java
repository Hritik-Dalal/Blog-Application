package com.hritik.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

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

}
