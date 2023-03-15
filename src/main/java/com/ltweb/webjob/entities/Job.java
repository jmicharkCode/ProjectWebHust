package com.ltweb.webjob.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "job")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "companyId")
    Company company;

    @JsonIgnore
    @OneToMany(mappedBy = "job",  cascade = CascadeType.ALL)
    private List<JobApplication> jobApps;

    @JsonIgnore
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Interview> interviews;

    @Column(name = "job_name")
    String jobName;

    @Column(name = "job_description")
    String jobDescription;

    @Column(name = "career")
    String career;

    @Column(name = "address")
    String address;

    @Column(name = "salary")
    int salary;

    @Column(name = "deadline")
    LocalDate deadline;

    @Column(name = "gender")
    Gender gender;

    @Column(name = "age")
    int age;

    @Column(name = "experience")
    String experience;

    @Column(name = "level")
    String level;

    @Column(name = "degree")
    Degree degree;

    @Column(name = "image")
    String image;

    @Column(name = "createdTime")
    LocalDate createdTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    StatusJob status;

    public Job (Company company,String jobName,String jobDescription,String career,String address,int salary,LocalDate deadline,Gender gender,int age,String experience,String level,Degree degree,String image,LocalDate createdTime,StatusJob status){

        this.company=company;
        this.jobName=jobName;
        this.jobDescription=jobDescription;
        this.career=career;
        this.address= address;
        this.salary=salary;
        this.deadline=deadline;
        this.gender=gender;
        this.age=age;
        this.experience=experience;
        this.level=level;
        this.degree=degree;
        this.image=image;
        this.createdTime=createdTime;
        this.status=status;

    }
}
