package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.StatusJA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationRequestDto {
    String cv;
    String experience;
}
