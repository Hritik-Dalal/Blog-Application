package com.hritik.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 3, message = "user name must be minimum of 3 Characters")
    private String name;

    @NotEmpty
    @Email(message = "Given email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 8, max=20, message = "Password must be between 3-15 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "Password must contain at least one small character (a-z), at least one large character (A-Z), at least one digit (0-9) and at least one special character (like @,etc)")
    private String password;

    @NotEmpty
    private String about;
}
