package com.ltweb.webjob.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDto {
    int id;
    JobDto job;
    String link;
    String description;
    LocalDateTime timeStart;
    LocalDateTime timeFinish;
    boolean isDeleted;
}
