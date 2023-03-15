package com.ltweb.webjob.controllers;

import com.ltweb.webjob.dtos.CandidateDto;
import com.ltweb.webjob.dtos.CandidateRequestDto;
import com.ltweb.webjob.dtos.CompanyDto;
import com.ltweb.webjob.dtos.CompanyRequestDto;
import com.ltweb.webjob.services.CandidateService;
import com.ltweb.webjob.services.CompanyService;
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
public class CompanyController {
    @Autowired
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = Objects.requireNonNull(companyService);
    }

    @PreAuthorize("hasAuthority({'CANDIDATE','ADMIN'})")
    @GetMapping("/admin/company")
    public ResponseEntity<List<CompanyDto>> getAll (){
        return new ResponseEntity<>(companyService.getAll(), HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority({'CANDIDATE','ADMIN'})")
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyDto> getById (@PathVariable int id){
        return new ResponseEntity<>(companyService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/company")
    public ResponseEntity<CompanyDto> getByUser (Principal principal){
        String email =principal.getName();
        return new ResponseEntity<>(companyService.getByUser(email), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY','ADMIN')")
    @PostMapping("/company")
    public ResponseEntity<CompanyDto> saveCompany (@RequestBody CompanyRequestDto companyRequestDto, Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(companyService.save(companyRequestDto,email), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PutMapping("/company")
    public ResponseEntity<CompanyDto> editCompany (@RequestBody CompanyRequestDto companyRequestDto,Principal principal){
        String email = principal.getName();
        return new ResponseEntity<>(companyService.edit(email,companyRequestDto), HttpStatus.OK);
    }
}
