package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Degree;
import com.ltweb.webjob.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestDto {
    String jobName;

    String jobDescription;

    String career;

    String address;

    int salary;

    String deadline;

    Gender gender;

    int age;

    String experience;

    String level;

    Degree degree;

    String image;
}
