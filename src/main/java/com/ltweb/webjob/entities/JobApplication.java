package com.ltweb.webjob.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "jobApplication")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "interview_id")
    int interviewId ;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "candidate_id")
    Candidate candidate ;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "job_id")
    Job job;

    @Column(name = "experience")
    private String experience;

    @Column(name = "feed_back")
    private String feedBack;

    @Column(name = "cv")
    private String cv;

//    @Column(name = "status")
//    StatusJA status;
    @Column(name = "status")
    private Integer status;
}
