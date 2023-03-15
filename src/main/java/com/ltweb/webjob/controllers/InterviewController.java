package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.*;
import com.ltweb.webjob.services.CompanyService;
import com.ltweb.webjob.services.InterviewService;
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
public class InterviewController {
    @Autowired
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = Objects.requireNonNull(interviewService);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
//    @GetMapping("/interview")
//    public ResponseEntity<List<InterviewDto>> getAll (){
//        return new ResponseEntity<>(interviewService.getAll(), HttpStatus.OK);
//    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/interview/{id}")
    public ResponseEntity<InterviewDto> getById (@PathVariable int id){
        return new ResponseEntity<>(interviewService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/interview")
    public ResponseEntity<List<InterviewDto>> getAllByJob (int jobId){
        return new ResponseEntity<>(interviewService.getAllByJob(jobId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/interview")
    public ResponseEntity<InterviewDto> saveInterview (@RequestBody InterviewRequestDto interviewRequestDto, int jobId){
        return new ResponseEntity<>(interviewService.save(interviewRequestDto,jobId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/interview")
    public ResponseEntity<InterviewDto> editInterview (@RequestBody InterviewRequestDto interviewRequestDto,int id){
        return new ResponseEntity<>(interviewService.edit(id,interviewRequestDto), HttpStatus.OK);
    }
}
