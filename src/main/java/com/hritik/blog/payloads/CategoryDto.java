package com.hritik.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int categoryId;

    @NotEmpty
    @Size(min = 3, max = 15, message = "title must be between 3 to 15 characters")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10, max = 100, message = "Description must be between 10 to 100 characters")
    private String categoryDescription;
}
