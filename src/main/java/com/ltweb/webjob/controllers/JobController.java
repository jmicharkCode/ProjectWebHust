package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.*;
import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.entities.StatusJA;
import com.ltweb.webjob.services.JobApplicationService;
import com.ltweb.webjob.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class JobController {
    @Autowired
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = Objects.requireNonNull(jobService);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/job")
    public ResponseEntity<List<JobDto>> getAll (){
        return new ResponseEntity<>(jobService.getAll(), HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/job/{id}")
    public ResponseEntity<JobDto> getById (@PathVariable int id){
        return new ResponseEntity<>(jobService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/company/job")
    public ResponseEntity<List<JobDto>> getAllByCompany (Principal principal){
        String email =principal.getName();
        return new ResponseEntity<>(jobService.getAllByCompany(email), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CANDIDATE')")
    @GetMapping("/candidate/searchJob")
    public ResponseEntity<List<JobDto>> getAllBySearchTerm (JobSearchTermDto jobSearchTermDto){
        return new ResponseEntity<>(jobService.getAllBySearchTerm(jobSearchTermDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/company/createJob")
    public ResponseEntity<JobDto> createJob (@RequestBody JobRequestDto jobRequestDto, Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(jobService.save(jobRequestDto,email), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/company/job/{id}")
    public ResponseEntity<JobDto> editJob (@RequestBody JobRequestDto jobRequestDto,@PathVariable int id){
        return new ResponseEntity<>(jobService.edit(id,jobRequestDto), HttpStatus.OK);
    }

    @GetMapping("/company/job_candidate/{jobId}")
    public ResponseEntity<?> getAllCandidate (@PathVariable String jobId){
        List<Candidate> list = jobService.getCandidateForJob(Integer.parseInt(jobId));
        if(list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("khong co list nao", HttpStatus.BAD_REQUEST);
        }
    }
}
