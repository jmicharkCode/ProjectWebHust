package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Degree;
import com.ltweb.webjob.entities.Gender;
import com.ltweb.webjob.entities.StatusJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {

    int id;

    CompanyDto company;

    String jobName;

    String jobDescription;

    String career;

    String address;

    int salary;

    LocalDate deadline;

    Gender gender;

    int age;

    String experience;

    String level;

    Degree degree;

    String image;

    LocalDate createdTime;

    StatusJob status;
}
