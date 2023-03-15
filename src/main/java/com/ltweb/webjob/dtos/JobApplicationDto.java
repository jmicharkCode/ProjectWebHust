package com.ltweb.webjob.dtos;

import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.StatusJA;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {
    int id;
    int interviewId;
    CandidateDto candidate;
    JobDto job;
    String cv;
    String feedBack;
    String experience;
    Integer status;
}
