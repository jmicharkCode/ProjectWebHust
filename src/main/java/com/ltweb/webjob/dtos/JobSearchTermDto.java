package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Degree;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSearchTermDto {
    String location;
    String career;
    Degree degree;
    String salary;
}
