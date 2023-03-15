package com.ltweb.webjob.services;

import com.ltweb.webjob.dtos.*;
import com.ltweb.webjob.entities.*;
import com.ltweb.webjob.exceptions.NotFoundException;
import com.ltweb.webjob.mappers.CandidateMapper;
import com.ltweb.webjob.mappers.JobApplicationMapper;
import com.ltweb.webjob.mappers.JobMapper;
import com.ltweb.webjob.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Service
public class JobService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    public List<JobDto> getAll(){
        return JobMapper.toDtoList(jobRepository.findAll());
    }

    public JobDto getById(int id){
        return  JobMapper.toDto(jobRepository.findById(id).orElseThrow(() -> new NotFoundException("JobApplication with id: " + id + " Not Found"))) ;
    }

    public List<JobDto> getAllBySearchTerm(JobSearchTermDto jobSearchTermDto){
        List<Job> jobs =new ArrayList<>();
        if (jobSearchTermDto.getDegree()!=null)
           jobs=jobRepository.findAllBySearchTerm(jobSearchTermDto.getLocation(),jobSearchTermDto.getCareer(),jobSearchTermDto.getDegree(), parseInt(jobSearchTermDto.getSalary()));
        else
            jobs=jobRepository.findAllBySearchTermNoDegree(jobSearchTermDto.getLocation(),jobSearchTermDto.getCareer(), parseInt(jobSearchTermDto.getSalary()));
        return JobMapper.toDtoList(jobs);
    }

    public List<JobDto> getAllByCompany (String email){
        User user =userRepository.findByEmail(email).orElseThrow();
        Company company = companyRepository.findByUser(user).orElseThrow();
        return JobMapper.toDtoList(jobRepository.findAllByCompany(company));
    }

    public JobDto save(JobRequestDto jobRequestDto, String email){
        User user= userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " Not Found"));
        Company company = companyRepository.findByUser(user).orElseThrow(() -> new NotFoundException("Company with email: " + email + " Not Found"));
        Job job = new Job();
        job.setCompany(company);
        job.setAddress(jobRequestDto.getAddress());
        job.setJobName(jobRequestDto.getJobName());
        job.setImage(jobRequestDto.getImage());
        job.setExperience(jobRequestDto.getExperience());
        job.setCareer(jobRequestDto.getCareer());
        job.setDeadline(LocalDate.parse(jobRequestDto.getDeadline()));
        job.setDegree(jobRequestDto.getDegree());
        job.setJobDescription(jobRequestDto.getJobDescription());
        job.setLevel(jobRequestDto.getLevel());
        job.setSalary(jobRequestDto.getSalary());
        job.setGender(jobRequestDto.getGender());
        job.setAge(jobRequestDto.getAge());
        job.setCreatedTime(LocalDate.now());
        job.setStatus(StatusJob.valueOf("DANGTUYEN"));
        return JobMapper.toDto(jobRepository.save(job));
    }

    public JobDto edit(int id, JobRequestDto jobRequestDto){
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Job with id: " + id + " Not Found"));
        job.setAddress(jobRequestDto.getAddress());
        job.setJobName(jobRequestDto.getJobName());
        job.setImage(jobRequestDto.getImage());
        job.setExperience(jobRequestDto.getExperience());
        job.setCareer(jobRequestDto.getCareer());
        job.setDeadline(LocalDate.parse(jobRequestDto.getDeadline()));
        job.setDegree(jobRequestDto.getDegree());
        job.setJobDescription(jobRequestDto.getJobDescription());
        job.setLevel(jobRequestDto.getLevel());
        job.setSalary(jobRequestDto.getSalary());
        job.setGender(jobRequestDto.getGender());
        job.setAge(jobRequestDto.getAge());
        return JobMapper.toDto(jobRepository.save(job));
    }

    public List<Candidate> getCandidateForJob(int jobId) {
        Optional<Job> jobOpt = jobRepository.findById(jobId);
        if(jobOpt.isPresent()) {
            List<JobApplication> jobApp = jobOpt.get().getJobApps();
            List<Candidate> listCandidate = new ArrayList<>();
            for(JobApplication j: jobApp) {
                listCandidate.add(j.getCandidate());
            }
            return listCandidate;
        }
        else  {
            return null;
        }
    }
}
