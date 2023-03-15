package com.ltweb.webjob.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltweb.webjob.entities.Degree;
import com.ltweb.webjob.entities.Gender;
import com.ltweb.webjob.entities.Role;
import com.ltweb.webjob.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;

    @NotNull
    private String email;

    @JsonIgnore
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull

    private LocalDate birthDate;

    @NotNull
    private Gender gender;

    @NotNull
    private Role role;

    @NotNull
    private LocalDateTime createdTime;

    @NotNull
    private String location;

    private Boolean isDeleted;

    public UserDto(String username, String password) {
        this.email = email;
        this.password = password;
    }
}
