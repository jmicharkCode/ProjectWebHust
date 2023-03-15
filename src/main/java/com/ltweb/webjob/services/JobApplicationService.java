package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.CandidateDto;
import com.ltweb.webjob.dtos.CandidateRequestDto;
import com.ltweb.webjob.dtos.JobApplicationDto;
import com.ltweb.webjob.dtos.JobApplicationRequestDto;
import com.ltweb.webjob.entities.*;
import com.ltweb.webjob.exceptions.ApiException;
import com.ltweb.webjob.exceptions.NotFoundException;
import com.ltweb.webjob.mappers.CandidateMapper;
import com.ltweb.webjob.mappers.JobApplicationMapper;
import com.ltweb.webjob.repositories.*;
import org.hibernate.tuple.component.CompositeBasedBasicAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    public List<JobApplicationDto> getAll(){
        return JobApplicationMapper.toDtoList(jobApplicationRepository.findAll());
    }

    public List<JobApplicationDto> getAllByCandidate(String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        Candidate candidate = candidateRepository.findByUser(user).orElseThrow();
        return JobApplicationMapper.toDtoList(jobApplicationRepository.findAllByCandidate(candidate));
    }

    public List<JobApplicationDto> getAllByJob(int jobId){
        Job job =jobRepository.findById(jobId).orElseThrow();
        return JobApplicationMapper.toDtoList(jobApplicationRepository.findAllByJob(job));
    }

    public JobApplicationDto getById(int id){
        return  JobApplicationMapper.toDto(jobApplicationRepository.findById(id).orElseThrow(() -> new NotFoundException("JobApplication with id: " + id + " Not Found"))) ;
    }

    public JobApplicationDto save(JobApplicationRequestDto jobApplicationRequestDto,String email, int jobId){
        User user = userRepository.findByEmail(email).orElseThrow();
        Candidate candidate = candidateRepository.findByUser(user).orElseThrow(() -> new NotFoundException("Candidate with email: " + email + " Not Found"));
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new NotFoundException("Job with id: " + jobId + " Not Found"));
        if(jobApplicationRepository.findAllByJobAndCandidate(job, candidate).isEmpty()) {
            JobApplication jobApplication = new JobApplication();
            jobApplication.setCv(jobApplicationRequestDto.getCv());
            jobApplication.setExperience(jobApplicationRequestDto.getExperience());
//        jobApplication.setInterview(new Interview());
//        jobApplication.setStatus(StatusJA.valueOf("DAUNGTUYEN"));
            jobApplication.setStatus(1);
            jobApplication.setJob(job);
            jobApplication.setCandidate(candidate);
            return JobApplicationMapper.toDto(jobApplicationRepository.save(jobApplication));
        }
        else{
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Job is existed");
        }

    }


    public JobApplicationDto addInterview(int id, int interviewId){
        JobApplication jobApplication = jobApplicationRepository.findById(id).orElseThrow(() -> new NotFoundException("JobApplication with id: " + id + " Not Found"));
//        Interview interview=interviewRepository.findById(interviewId).orElseThrow(() -> new NotFoundException("Interview with id: " + interviewId + " Not Found"));
        jobApplication.setInterviewId(interviewId);
        return JobApplicationMapper.toDto(jobApplicationRepository.save(jobApplication));
    }

//    public JobApplicationDto editStatus(int id, StatusJA status){
//        JobApplication jobApplication = jobApplicationRepository.findById(id).orElseThrow(() -> new NotFoundException("JobApplication with email: " + id + " Not Found"));
//        jobApplication.setStatus(status);
//        return JobApplicationMapper.toDto(jobApplicationRepository.save(jobApplication));
//    }

    public JobApplicationDto editStatus(int id, int status){
        JobApplication jobApplication = jobApplicationRepository.findById(id).orElseThrow(() -> new NotFoundException("JobApplication with id: " + id + " Not Found"));
        jobApplication.setStatus(status);
        return JobApplicationMapper.toDto(jobApplicationRepository.save(jobApplication));
    }

    public JobApplicationDto createFeedBack(int id, String feedBack){
        JobApplication jobApplication = jobApplicationRepository.findById(id).orElseThrow(() -> new NotFoundException("JobApplication with email: " + id + " Not Found"));
        jobApplication.setFeedBack(feedBack);
        return JobApplicationMapper.toDto(jobApplicationRepository.save(jobApplication));
    }
}
