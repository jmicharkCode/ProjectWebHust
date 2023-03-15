package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.CandidateDto;
import com.ltweb.webjob.dtos.CandidateRequestDto;
import com.ltweb.webjob.dtos.CompanyDto;
import com.ltweb.webjob.entities.Candidate;
import com.ltweb.webjob.services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class CandidateController {
    @Autowired
    private final CandidateService candidateService;

    public CandidateController(CandidateService service){
        this.candidateService = Objects.requireNonNull(service);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/candidates")
    public ResponseEntity<List<CandidateDto>> getAll (){
        return new ResponseEntity<>(candidateService.getAll(),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CANDIDATE')")
    @GetMapping("/candidate")
    public ResponseEntity<CandidateDto> getByUser (Principal principal){
        String email =principal.getName();
        return new ResponseEntity<>(candidateService.getByUser(email), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('CANDIDATE','ADMIN')")
    @GetMapping("/candidate/{id}")
    public ResponseEntity<CandidateDto> getById (int id){
        return new ResponseEntity<>(candidateService.getById(id), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/company/searchCandidate")
    public ResponseEntity<List<CandidateDto>> getAllBySearchTerm (CandidateRequestDto candidateRequestDto){
        return new ResponseEntity<>(candidateService.getAllBySearchTerm(candidateRequestDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CANDIDATE')")
    @PostMapping("/candidate")
    public ResponseEntity<CandidateDto> saveCandidate (@RequestBody CandidateRequestDto candidateRequestDto, Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(candidateService.save(candidateRequestDto,email), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('CANDIDATE')")
    @PutMapping("/candidate")
    public ResponseEntity<CandidateDto> editCandidate (@RequestBody CandidateRequestDto candidateRequestDto, Principal principal){

        String email = principal.getName();
        return new ResponseEntity<>(candidateService.edit(candidateRequestDto,email), HttpStatus.OK);
    }

}
