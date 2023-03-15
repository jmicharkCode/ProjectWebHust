package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Degree;
import com.ltweb.webjob.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {

    private int id;

    private UserDto user;

    private Degree degree;

    private String career;
}
