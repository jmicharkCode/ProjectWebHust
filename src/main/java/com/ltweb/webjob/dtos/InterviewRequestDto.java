package com.ltweb.webjob.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class InterviewRequestDto {
    String link;
    String description;
    String timeStart;
    String timeFinish;
}
