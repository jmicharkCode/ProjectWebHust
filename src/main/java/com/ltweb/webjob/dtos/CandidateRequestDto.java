package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Degree;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateRequestDto {

    Degree degree;

    String career;
}
