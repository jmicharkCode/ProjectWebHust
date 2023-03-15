package com.ltweb.webjob.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltweb.webjob.entities.Gender;
import com.ltweb.webjob.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    @NotEmpty(message = "username is required")
    private String username;

    @Size(min = 2, max = 255)
    private String email;
    @NotEmpty(message = "password is required")
    @Size(min = 8, max = 64)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String birthDate;

    @NotNull
    private Gender gender;

    @NotNull
    private String location;

    @NotNull
    private Role role;
}
