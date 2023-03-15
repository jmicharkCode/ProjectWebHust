package com.ltweb.webjob.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "email is required")
    @Size(min = 2, max = 255)
    private String email;

    @NotEmpty(message = "password is required")
    @Size(min = 8, max = 64)
    private String password;
}
