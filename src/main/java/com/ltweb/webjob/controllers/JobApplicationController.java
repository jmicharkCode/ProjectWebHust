package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.InterviewDto;
import com.ltweb.webjob.dtos.InterviewRequestDto;
import com.ltweb.webjob.dtos.JobApplicationDto;
import com.ltweb.webjob.dtos.JobApplicationRequestDto;
import com.ltweb.webjob.entities.StatusJA;
import com.ltweb.webjob.entities.User;
import com.ltweb.webjob.services.InterviewService;
import com.ltweb.webjob.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class JobApplicationController {
    @Autowired
    private final JobApplicationService jobApplicationService;

    public JobApplicationController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = Objects.requireNonNull(jobApplicationService);
    }

//    @PreAuthorize("hasAuthority('CANDIDATE')")
//    @GetMapping("/jobApplication")
//    public ResponseEntity<List<JobApplicationDto>> getAll (){
//        return new ResponseEntity<>(jobApplicationService.getAll(), HttpStatus.OK);
//    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/jobApplication/{id}")
    public ResponseEntity<JobApplicationDto> getById (@PathVariable int id){
        return new ResponseEntity<>(jobApplicationService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CANDIDATE')")
    @GetMapping("/candidate/jobApplication")
    public ResponseEntity<List<JobApplicationDto>> getAllByCandidate (Principal principal){
        String email= principal.getName();
        return new ResponseEntity<>(jobApplicationService.getAllByCandidate(email), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/job/jobApplication")
    public ResponseEntity<List<JobApplicationDto>> getAllByJob (int jobId){
        return new ResponseEntity<>(jobApplicationService.getAllByJob(jobId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CANDIDATE')")
    @PostMapping("/candidate/jobApplication")
    public ResponseEntity<JobApplicationDto> createJobApplication (@RequestBody JobApplicationRequestDto jobApplicationRequestDto,int jobId,Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(jobApplicationService.save(jobApplicationRequestDto,email,jobId), HttpStatus.OK);
    }



//    @PreAuthorize("hasAuthority('CANDIDATE')")
//    @PutMapping("/candidate/jobApplication/{id}/editStatus")
//    public ResponseEntity<JobApplicationDto> editJobApplication (@PathVariable int id,StatusJA status){
//        return new ResponseEntity<>(jobApplicationService.editStatus(id,status), HttpStatus.OK);
//    }

    @PreAuthorize("hasAuthority('CANDIDATE')")
    @PutMapping("/candidate/jobApplication/addInterview")
    public ResponseEntity<JobApplicationDto> addInterview (int id, int interviewId){
        return new ResponseEntity<>(jobApplicationService.addInterview(id,interviewId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('CANDIDATE','COMPANY')")
    @PutMapping("/jobApplication/editStatus")
    public ResponseEntity<JobApplicationDto> editJobApplication (int id,int status){
        return new ResponseEntity<>(jobApplicationService.editStatus(id,status), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('COMPANY')")
    @PutMapping("/company/jobApplication/createFeedBack")
    public ResponseEntity<JobApplicationDto> createFeedBack (int id, @RequestBody String feedBack){
        return new ResponseEntity<>(jobApplicationService.createFeedBack(id,feedBack), HttpStatus.OK);
    }
}
