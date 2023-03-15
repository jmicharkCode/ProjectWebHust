package com.ltweb.webjob.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ChangePasswordDto {

    @Size(min = 8, max = 64)
    private String oldPassword;

    @NotEmpty(message = "new password is required")
    @Size(min = 8, max = 64)
    private String newPassword;
}
