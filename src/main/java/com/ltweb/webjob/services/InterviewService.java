package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.InterviewDto;
import com.ltweb.webjob.dtos.InterviewRequestDto;
import com.ltweb.webjob.dtos.JobApplicationDto;
import com.ltweb.webjob.dtos.JobApplicationRequestDto;
import com.ltweb.webjob.entities.*;
import com.ltweb.webjob.exceptions.NotFoundException;
import com.ltweb.webjob.mappers.InterviewMapper;
import com.ltweb.webjob.mappers.JobApplicationMapper;
import com.ltweb.webjob.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InterviewService {


    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    public List<InterviewDto> getAll(){
        return InterviewMapper.toDtoList(interviewRepository.findAll());
    }

    public InterviewDto getById(int id){
        return  InterviewMapper.toDto(interviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Interview with id: " + id + " Not Found"))) ;
    }
    public List<InterviewDto> getAllByJob(int jobId){
        Job job = jobRepository.findById(jobId).orElseThrow();
        return  InterviewMapper.toDtoList(interviewRepository.findAllByJob(job)) ;
    }


    public InterviewDto save(InterviewRequestDto interviewRequestDto, int jobId){
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new NotFoundException("Job with id: " + jobId + " Not Found"));
        Interview interview = new Interview();
        interview.setDescription(interviewRequestDto.getDescription());
        interview.setLink(interviewRequestDto.getLink());
        interview.setJob(job);
        interview.setTimeStart(LocalDateTime.parse(interviewRequestDto.getTimeStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        interview.setTimeFinish(LocalDateTime.parse(interviewRequestDto.getTimeFinish(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return InterviewMapper.toDto(interviewRepository.save(interview));
    }

    public InterviewDto edit(int id, InterviewRequestDto interviewRequestDto){
        Interview interview = interviewRepository.findById(id).orElseThrow(() -> new NotFoundException("Interview with id: " + id + " Not Found"));
        interview.setDescription(interviewRequestDto.getDescription());
        interview.setLink(interviewRequestDto.getLink());
        interview.setTimeStart(LocalDateTime.parse(interviewRequestDto.getTimeStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        interview.setTimeFinish(LocalDateTime.parse(interviewRequestDto.getTimeFinish(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return InterviewMapper.toDto(interviewRepository.save(interview));
    }



}
