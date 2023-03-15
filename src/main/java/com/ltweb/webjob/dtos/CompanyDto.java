package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    int id;

    UserDto user;

    String companyName;

    String companyWebsite;

    String companyLogo;

    String companyDescription;

    String companyImage;
}
