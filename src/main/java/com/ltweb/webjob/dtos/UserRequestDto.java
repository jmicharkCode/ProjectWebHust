package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Gender;
import com.ltweb.webjob.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @NotNull
    private String birthDate;

    @NotNull
    private String location;

    @NotNull
    private Gender gender;


}
